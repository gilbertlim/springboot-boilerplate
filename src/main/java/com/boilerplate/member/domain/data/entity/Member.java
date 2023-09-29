package com.boilerplate.member.domain.data.entity;

import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Member {

    private Integer code;
    private String id;
    private String password;
    private String name;
    private String roles;
    private LocalDateTime lastLoginDateTime;
    private LocalDateTime registrationDateTime;

    public Member(String id, String password, String name, String roles) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }
}
