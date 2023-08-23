package com.megazone.springbootboilerplate.common.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class SchedulingLogAspect {

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        log.info("{} 스케줄러 시작", method);
        try {
            Object result = joinPoint.proceed();
            log.info("{} 스케줄러 종료", method);
            return result;
        } catch (Throwable e) {
            log.error("{} 스케줄러 예외", method, e);
            throw new RuntimeException(e);
        }
    }
}
