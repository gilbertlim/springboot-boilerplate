package com.boilerplate.common.config.security.auth.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.boilerplate.common.dto.CommonResponse;
import com.boilerplate.common.dto.CommonResponseType;
import com.boilerplate.common.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        var body = CommonResponse.error(CommonResponseType.UNAUTHORIZED, new BadCredentialsException("유효하지 않은 토큰입니다.")).getBody();
        ResponseUtils.setResponse(response, CommonResponseType.UNAUTHORIZED.getStatus(), body);
    }
}
