package com.boilerplate.member.domain.port.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.boilerplate.member.domain.data.entity.Member;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(String memberId);

    @Transactional
    void updateLastLoginDateTime(String memberId);
}
