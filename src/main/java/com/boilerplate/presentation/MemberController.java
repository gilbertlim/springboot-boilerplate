package com.boilerplate.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.boilerplate.common.dto.CommonResponse;


/**
 * 인증 테스트를 위한 임시 컨트롤러
 */
@RequiredArgsConstructor
@RestController
public class MemberController {

    @GetMapping("/members/me")
    public ResponseEntity<CommonResponse<String>> me(@AuthenticationPrincipal String principal) {
        return CommonResponse.ok("Hello " + principal);
    }

    @GetMapping("/members/admin")
    public ResponseEntity<CommonResponse<String>> admin(@AuthenticationPrincipal String principal) {
        return CommonResponse.ok("Hello " + principal);
    }
}
