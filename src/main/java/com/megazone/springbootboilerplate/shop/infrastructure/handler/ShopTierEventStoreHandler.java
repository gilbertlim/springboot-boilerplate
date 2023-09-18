package com.megazone.springbootboilerplate.shop.infrastructure.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.megazone.springbootboilerplate.shop.domain.event.ShopTierEvent;
import com.megazone.springbootboilerplate.shop.infrastructure.adaptor.repository.ShopEventRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShopTierEventStoreHandler {

    private final ShopEventRepository shopEventRepository;

    @TransactionalEventListener(value = ShopTierEvent.class, phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(ShopTierEvent event) {
        shopEventRepository.save(event);
        log.info("Event stored successfully: {}", event.toString());
    }
}
