package com.megazone.springbootboilerplate.common.config.schedule;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = {"scheduler.enabled", "schedule.groups.b.enabled"}, havingValue = "true")
public class SchedulerJobGroupB {

    @Scheduled(cron = "*/1 * * * * *")
    @SchedulerLock(name = "schedulerLock3", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    public void aSchedule1() {
        log.info("[{}] 스케줄러 작동: {}", Thread.currentThread().getName(), new Date());
    }

}
