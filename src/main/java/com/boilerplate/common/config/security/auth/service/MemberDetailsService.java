package com.boilerplate.common.config.security.auth.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.boilerplate.member.domain.data.entity.Member;
import com.boilerplate.member.domain.port.repository.MemberRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> {
                log.error("회원을 찾을 수 없습니다.");
                throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
                // TODO: stack trace 찍히는 방법 확인
            });

        return User.builder()
            .username(member.getId())
            .password(member.getPassword())
            .roles(member.getRoles())
            .build();
    }
}
