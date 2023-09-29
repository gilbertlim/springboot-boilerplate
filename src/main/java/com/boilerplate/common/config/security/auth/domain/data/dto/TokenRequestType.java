package com.boilerplate.common.config.security.auth.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TokenRequestType {

    TOKEN, REFRESH;

    @JsonCreator
    public static TokenRequestType of(String symbol) {
        return TokenRequestType.valueOf(symbol.toUpperCase());
    }
}
