package com.megazone.springbootboilerplate.common.config.scheduling;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = {"scheduling.enabled", "scheduling.groups.demoA.enabled"}, havingValue = "true")
public class SchedulingGroupDemoA {

    @Scheduled(cron = "*/5 * * * * *")
    @SchedulerLock(name = "schedulerLockA", lockAtMostFor = "5s", lockAtLeastFor = "5s")
    public void scheduleA() {
        log.info("[{}] 스케줄러 작동: {}", Thread.currentThread().getName(), new Date());
    }

}
