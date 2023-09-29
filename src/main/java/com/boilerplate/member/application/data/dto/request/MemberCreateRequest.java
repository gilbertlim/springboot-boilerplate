package com.boilerplate.member.application.data.dto.request;

public record MemberCreateRequest(

    String id,
    String password,
    String name,
    String roles
) {
}
