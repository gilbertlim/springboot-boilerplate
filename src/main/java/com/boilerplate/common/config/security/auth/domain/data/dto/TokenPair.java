package com.boilerplate.common.config.security.auth.domain.data.dto;

public record TokenPair(

    String accessToken,
    String refreshToken
) {
}
