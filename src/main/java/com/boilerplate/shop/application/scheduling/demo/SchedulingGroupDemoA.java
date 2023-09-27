package com.boilerplate.shop.application.scheduling.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import com.boilerplate.common.config.scheduling.annotation.SchedulingGrouping;
import com.boilerplate.common.utils.DateTimeUtils;

@Slf4j
@RequiredArgsConstructor
@Component
@SchedulingGrouping({"demoA"})
public class SchedulingGroupDemoA {

    @Scheduled(cron = "*/30 * * * * *")
    @SchedulerLock(name = "schedulerLockA", lockAtMostFor = "29s", lockAtLeastFor = "29s")
    public void scheduleA() {
        log.info("[{}] 스케줄러 실행: {}", Thread.currentThread().getName(), DateTimeUtils.nowFormat());
    }

}
