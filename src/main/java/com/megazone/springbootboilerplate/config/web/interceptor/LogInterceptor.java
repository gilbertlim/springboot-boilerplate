package com.megazone.springbootboilerplate.config.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogInterceptor implements HandlerInterceptor {

    public static final String RESPONSE_TIME = "responseTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();
        log.info("[{}][{}][{}] Request", method, requestUri, queryString);

        String accept = request.getHeader("accept");
        String userAgent = request.getHeader("user-agent");
        log.info("[Request Headers] [Accept: {}][UserAgent: {}]", accept, userAgent);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        Long responseTime = (Long) request.getAttribute(RESPONSE_TIME);
        log.info("[{}][{}] Response {}ms", method, requestUri, responseTime);
    }
}
