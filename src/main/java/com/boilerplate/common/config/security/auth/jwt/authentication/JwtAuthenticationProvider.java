package com.boilerplate.common.config.security.auth.jwt.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.boilerplate.common.config.security.auth.jwt.JwtProcessor;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider {

    private final JwtProcessor jwtProcessor;

    public Authentication authenticate(String token) {
        if (!jwtProcessor.isValidToken(token)) {
            return JwtAuthenticationToken.unauthenticatedToken();
        }

        return JwtAuthenticationToken.authenticatedToken(jwtProcessor.getId(token), token, jwtProcessor.getAuthorities(token));
    }

}
