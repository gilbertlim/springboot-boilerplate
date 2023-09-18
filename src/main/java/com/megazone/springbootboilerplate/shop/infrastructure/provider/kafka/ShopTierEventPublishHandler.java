package com.megazone.springbootboilerplate.shop.infrastructure.provider.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.springbootboilerplate.shop.domain.event.ShopTierEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShopTierEventPublishHandler {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @TransactionalEventListener(value = ShopTierEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ShopTierEvent event) {
        kafkaTemplate.send("shop-tier-event", event);
        log.info("Event published successfully: {}", event.toString());
    }
}
