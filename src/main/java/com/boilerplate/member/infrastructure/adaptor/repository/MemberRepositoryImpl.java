package com.boilerplate.member.infrastructure.adaptor.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import com.boilerplate.member.domain.data.entity.Member;
import com.boilerplate.member.domain.port.repository.MemberRepository;
import com.boilerplate.member.infrastructure.provider.mybatis.mapper.MemberMapper;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.ofNullable(memberMapper.findById(memberId));
    }

    @Override
    public void updateLastLoginDateTime(String memberId) {
        memberMapper.updateLastLoginDateTime(memberId);
    }
}
