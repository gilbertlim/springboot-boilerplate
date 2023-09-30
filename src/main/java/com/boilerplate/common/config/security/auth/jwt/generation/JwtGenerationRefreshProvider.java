package com.boilerplate.common.config.security.auth.jwt.generation;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.boilerplate.common.config.security.auth.domain.data.dto.TokenPair;
import com.boilerplate.common.config.security.auth.jwt.JwtProcessor;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationToken;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtGenerationRefreshProvider implements AuthenticationProvider {

    private final ObjectMapper objectMapper;
    private final JwtProcessor jwtProcessor;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String result = values.get(authenticationToken.getName());
        String errorMsg = "";

        if (!StringUtils.hasText(result)) {
            errorMsg = "갱신정보가 없습니다.";
            log.error(errorMsg);
            throw new BadCredentialsException(errorMsg);
        }

        TokenPair existedTokenPair;
        try {
            existedTokenPair = objectMapper.readValue(result, TokenPair.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json 파싱 오류");
        }

        if (!existedTokenPair.refreshToken().equals(authenticationToken.getTokenPair().refreshToken())) {
            errorMsg = "갱신 정보가 올바르지 않습니다.";
            log.error(errorMsg);
            throw new BadCredentialsException(errorMsg);
        }

        String existedAccessToken = existedTokenPair.accessToken();
        String id = jwtProcessor.getId(existedAccessToken);
        var roles = jwtProcessor.getAuthorities(existedAccessToken);

        TokenPair newTokenPair = jwtProcessor.createToken(id, roles);
        return JwtAuthenticationToken.authenticatedToken(id, newTokenPair, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
