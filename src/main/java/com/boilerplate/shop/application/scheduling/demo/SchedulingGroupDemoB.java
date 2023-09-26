package com.boilerplate.shop.application.scheduling.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.boilerplate.common.config.scheduling.annotation.SchedulingGrouping;
import com.boilerplate.common.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Slf4j
@Component
@SchedulingGrouping({"demoB"})
public class SchedulingGroupDemoB {

    @Scheduled(cron = "*/60 * * * * *")
    @SchedulerLock(name = "schedulerLockB", lockAtMostFor = "59s", lockAtLeastFor = "59s")
    public void scheduleB() {
        log.info("[{}] 스케줄러 실행: {}", Thread.currentThread().getName(), DateTimeUtils.nowFormat());
    }

}
