package com.boilerplate.common.config.security.auth.data.dto;

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
