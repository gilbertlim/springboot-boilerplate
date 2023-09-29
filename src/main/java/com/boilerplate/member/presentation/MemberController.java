package com.boilerplate.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.boilerplate.common.dto.CommonResponse;
import com.boilerplate.member.application.data.dto.request.MemberCreateRequest;
import com.boilerplate.member.application.data.dto.response.MemberResponse;
import com.boilerplate.member.application.service.MemberReadService;
import com.boilerplate.member.application.service.MemberWriteService;


/**
 * 인증 테스트를 위한 임시 컨트롤러
 */
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;

    @PostMapping("/members")
    public ResponseEntity<CommonResponse<MemberResponse>> create(@RequestBody MemberCreateRequest request) {
        MemberResponse memberResponse = memberWriteService.create(request);
        return CommonResponse.created(memberResponse, "/members/" + memberResponse.code());
    }

    @GetMapping("/members/me")
    public ResponseEntity<CommonResponse<String>> me(@AuthenticationPrincipal String principal) {
        return CommonResponse.ok("Hello " + principal);
    }

    @GetMapping("/members/admin")
    public ResponseEntity<CommonResponse<String>> admin(@AuthenticationPrincipal String principal) {
        return CommonResponse.ok("Hello " + principal);
    }
}
