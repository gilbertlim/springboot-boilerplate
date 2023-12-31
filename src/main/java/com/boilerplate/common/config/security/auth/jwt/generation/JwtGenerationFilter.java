package com.boilerplate.common.config.security.auth.jwt.generation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.boilerplate.common.config.security.auth.domain.data.dto.TokenPair;
import com.boilerplate.common.config.security.auth.domain.data.dto.TokenRequest;
import com.boilerplate.common.config.security.auth.jwt.JwtProperties;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationToken;
import com.boilerplate.common.dto.CommonResponse;
import com.boilerplate.common.dto.CommonResponseType;
import com.boilerplate.common.utils.ResponseUtils;
import com.boilerplate.member.domain.port.repository.MemberRepository;

public class JwtGenerationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final MemberRepository memberRepository;

    private final JwtProperties jwtProperties;

    public JwtGenerationFilter(
        AuthenticationManager authenticationManager,
        ObjectMapper objectMapper,
        RedisTemplate<String, String> redisTemplate,
        MemberRepository memberRepository,
        JwtProperties jwtProperties
    ) {
        super("/login", authenticationManager);
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
        this.memberRepository = memberRepository;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher.antMatcher(HttpMethod.POST, filterProcessesUrl));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        TokenRequest tokenRequest = objectMapper.readValue(request.getInputStream(), TokenRequest.class);

        Authentication authentication = createAuthenticationFrom(tokenRequest);
        return getAuthenticationManager().authenticate(authentication);
    }

    private Authentication createAuthenticationFrom(TokenRequest tokenRequest) {
        if (tokenRequest.isRefreshRequest()) {
            return JwtAuthenticationToken.refreshToken(tokenRequest.id(), tokenRequest.refreshToken());
        }
        return new UsernamePasswordAuthenticationToken(tokenRequest.id(), tokenRequest.password());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        var authenticationToken = (JwtAuthenticationToken) authResult;
        TokenPair tokenPair = authenticationToken.getTokenPair();
        var body = CommonResponse.ok(tokenPair).getBody();
        ResponseUtils.setResponse(response, HttpStatus.OK, body);

        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(tokenPair.refreshToken(), tokenPair.accessToken(), jwtProperties.refresh().expirationTime());

        memberRepository.updateLastLoginDateTime(authenticationToken.getName());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        var body = CommonResponse.error(CommonResponseType.UNAUTHORIZED, failed).getBody();
        ResponseUtils.setResponse(response, CommonResponseType.UNAUTHORIZED.getStatus(), body);
    }
}
