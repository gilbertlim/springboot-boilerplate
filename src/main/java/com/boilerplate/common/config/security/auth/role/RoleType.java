package com.boilerplate.common.config.security.auth.role;

import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private final String name;

    RoleType(String name) {
        this.name = name;
    }
}
