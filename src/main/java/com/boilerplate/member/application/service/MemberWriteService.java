package com.boilerplate.member.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.boilerplate.common.exception.DuplicateDataException;
import com.boilerplate.member.application.data.dto.request.MemberCreateRequest;
import com.boilerplate.member.application.data.dto.response.MemberResponse;
import com.boilerplate.member.application.data.mapper.MemberFieldMapper;
import com.boilerplate.member.domain.data.entity.Member;
import com.boilerplate.member.domain.port.repository.MemberRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponse create(MemberCreateRequest request) {
        memberRepository.findById(request.id())
            .ifPresent(it -> {
                throw new DuplicateDataException("'" + request.id() + "'는 중복된 아이디입니다.");
            });

        String encoded_password = passwordEncoder.encode(request.password());
        Member member = new Member(request.id(), encoded_password, request.name(), request.roles());
        memberRepository.save(member);

        return MemberFieldMapper.INSTANCE.toMemberResponse(member);
    }
}
