package com.megazone.springbootboilerplate.common.config.schedule;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerJob {

    @Scheduled(cron = "*/10 * * * * *")
    @SchedulerLock(name = "schedulerLock", lockAtMostFor = "10s", lockAtLeastFor = "10s")
    public void scheduler() {
        log.info("[{}] 스케줄러 작동: {}", Thread.currentThread().getName(), new Date());
    }
}
