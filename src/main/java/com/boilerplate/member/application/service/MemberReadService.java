package com.boilerplate.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.boilerplate.member.domain.port.repository.MemberRepository;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberReadService {

    MemberRepository memberRepository;

    //public Optional<>

}
