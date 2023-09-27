package com.boilerplate.common.config.security.auth.data.dto;

public record TokenPair(

    String accessToken,
    String refreshToken
) {
}
