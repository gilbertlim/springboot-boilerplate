package com.megazone.springbootboilerplate.config.datasource;

import lombok.Getter;

import java.util.Objects;

@Getter
abstract class AbstractDataSourceInfo {
    private final String url;
    private final String username;
    private final String password;

    public AbstractDataSourceInfo(String url, String username, String password) {
        this.url = Objects.requireNonNull(url);
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNullElse(password, "");
    }

    abstract public boolean isReadOnly();
}
