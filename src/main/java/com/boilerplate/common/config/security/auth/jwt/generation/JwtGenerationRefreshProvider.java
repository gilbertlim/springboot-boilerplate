package com.boilerplate.common.config.security.auth.jwt.generation;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.boilerplate.common.config.security.auth.domain.data.dto.TokenPair;
import com.boilerplate.common.config.security.auth.jwt.JwtProcessor;
import com.boilerplate.common.config.security.auth.jwt.authentication.JwtAuthenticationToken;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtGenerationRefreshProvider implements AuthenticationProvider {

    private final JwtProcessor jwtProcessor;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        ValueOperations<String, String> values = redisTemplate.opsForValue();

        String existedAccessToken = Optional.ofNullable(values.get(authenticationToken.getTokenPair().refreshToken()))
            .orElseThrow(() -> new BadCredentialsException("갱신정보가 없습니다."));

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
