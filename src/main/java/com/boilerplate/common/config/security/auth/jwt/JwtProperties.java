package com.boilerplate.common.config.security.auth.jwt;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String secretKey,
    TokenConfig access,
    TokenConfig refresh
) {

    public record TokenConfig(
        Duration expirationTime
    ) {
    }
}
