package com.boilerplate.common.config.security.auth.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.boilerplate.common.dto.CommonResponse;
import com.boilerplate.common.dto.CommonResponseType;
import com.boilerplate.common.utils.ResponseUtils;


@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        var body = CommonResponse.error(CommonResponseType.FORBIDDEN, accessDeniedException).getBody();
        ResponseUtils.setResponse(response, CommonResponseType.FORBIDDEN.getStatus(), body);
    }
}
