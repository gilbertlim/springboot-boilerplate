package com.boilerplate.shop.infrastructure.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import com.boilerplate.shop.domain.event.ShopTierEvent;
import com.boilerplate.shop.infrastructure.adaptor.repository.ShopEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
