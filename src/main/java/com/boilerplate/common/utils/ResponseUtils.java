package com.boilerplate.common.utils;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> void setResponse(HttpServletResponse response, HttpStatus status, T body) {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(OBJECT_MAPPER.writeValueAsString(body));
        } catch (IOException e) {
            throw new IllegalArgumentException(body.getClass().getSimpleName() + " JSON 직렬화 실패");
        }
    }
}
