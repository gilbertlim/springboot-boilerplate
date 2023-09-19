package com.boilerplate.shop.domain.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopTierEvent {

    Integer eventId;
    EventType eventType;
    Long domainId;

    private ShopTierEvent(Long domainId, EventType eventType) {
        this.domainId = domainId;
        this.eventType = eventType;
    }

    public static ShopTierEvent tierCreated(Long domainId) {
        return new ShopTierEvent(domainId, EventType.CREATED);
    }

    public static ShopTierEvent tierChanged(Long domainId) {
        return new ShopTierEvent(domainId, EventType.CHANGED);
    }

    public enum EventType {
        CREATED, CHANGED
    }
}