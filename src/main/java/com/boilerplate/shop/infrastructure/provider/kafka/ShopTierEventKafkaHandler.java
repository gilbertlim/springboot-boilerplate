package com.boilerplate.shop.infrastructure.provider.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.boilerplate.shop.domain.event.ShopTierEvent;
import com.boilerplate.shop.infrastructure.provider.mybatis.mapper.ShopEventMapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShopTierEventKafkaHandler {

    private final ShopEventMapper shopEventMapper;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "shop-tier-event")
    public void handle(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info("{} message consumed, key: {}, value: {}", record.topic(), record.key(), record.value());

        ShopTierEvent shopMemberEvent = mapper.readValue(record.value(), ShopTierEvent.class);
        shopEventMapper.updateToPublished(shopMemberEvent);
    }
}
