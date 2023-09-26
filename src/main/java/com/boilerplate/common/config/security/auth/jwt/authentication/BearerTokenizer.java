package com.boilerplate.common.config.security.auth.jwt.authentication;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

import org.springframework.http.HttpHeaders;

public class BearerTokenizer {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final int TOKEN_INDEX = 1;

    public Optional<String> token(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
            .filter(this::hasToken)
            .map(this::extractedToken);
    }

    private boolean hasToken(String header) {
        return header.startsWith(BEARER_PREFIX) && header.length() != BEARER_PREFIX.length();
    }

    private String extractedToken(String bearerToken) {
        return bearerToken.split(" ")[TOKEN_INDEX];
    }
}
