package com.boilerplate.member.application.data.dto.response;

import com.boilerplate.member.domain.data.entity.Member;

public record MemberResponse(

    Integer code,
    String id,
    String name,
    String roles
) {

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getCode(),
            member.getId(),
            member.getPassword(),
            member.getRoles());
    }
}
