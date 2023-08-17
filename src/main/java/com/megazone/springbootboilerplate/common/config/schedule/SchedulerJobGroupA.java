package com.megazone.springbootboilerplate.common.config.schedule;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = {"scheduler.enabled", "scheduler.groups.a.enabled"}, havingValue = "true")
public class SchedulerJobGroupA {

    @Scheduled(cron = "*/5 * * * * *")
    @SchedulerLock(name = "schedulerLock", lockAtMostFor = "5s", lockAtLeastFor = "5s")
    public void aSchedule1() {
        log.info("[{}] 스케줄러 작동: {}", Thread.currentThread().getName(), new Date());
    }

}
