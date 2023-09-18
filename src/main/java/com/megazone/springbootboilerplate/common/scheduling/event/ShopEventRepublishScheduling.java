package com.megazone.springbootboilerplate.common.scheduling.event;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import com.megazone.springbootboilerplate.common.utils.DateTimeUtils;
import com.megazone.springbootboilerplate.shop.domain.event.ShopTierEvent;
import com.megazone.springbootboilerplate.shop.infrastructure.provider.kafka.ShopTierEventPublishHandler;
import com.megazone.springbootboilerplate.shop.infrastructure.provider.mybatis.mapper.ShopEventMapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShopEventRepublishScheduling {

    private final ShopTierEventPublishHandler shopMemberEventPublishHandler;
    private final ShopEventMapper shopEventMapper;

    @Scheduled(cron = "*/10 * * * * *")
    @SchedulerLock(name = "schedulerLockShopEvent", lockAtMostFor = "9s", lockAtLeastFor = "9s")
    public void scheduling() {
        log.info("[{}] Kafka로 발행되지 않은 이벤트 재발송 배치 실행: {}", Thread.currentThread().getName(), DateTimeUtils.nowFormat());

        List<ShopTierEvent> notPublishedEvents = shopEventMapper.findByNotPublished();
        notPublishedEvents.forEach(shopMemberEventPublishHandler::handle);
    }
}
