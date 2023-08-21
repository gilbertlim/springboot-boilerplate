package com.megazone.springbootboilerplate.common.config.scheduling;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(name = {"scheduling.enabled", "scheduling.groups.demoB.enabled"}, havingValue = "true")
public class SchedulingJobGroupDemoB {

    @Scheduled(cron = "*/1 * * * * *")
    @SchedulerLock(name = "schedulerLockB", lockAtMostFor = "1s", lockAtLeastFor = "1s")
    public void scheduleB() {
        log.info("[{}] 스케줄러 작동: {}", Thread.currentThread().getName(), new Date());
    }

}
