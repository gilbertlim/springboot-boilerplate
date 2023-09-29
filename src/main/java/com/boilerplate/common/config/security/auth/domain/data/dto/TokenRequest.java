package com.boilerplate.common.config.security.auth.domain.data.dto;

public record TokenRequest(

    TokenRequestType type,
    String id,
    String password,
    String refreshToken
) {

    public boolean isRefreshRequest() {
        return type == TokenRequestType.REFRESH;
    }
}
