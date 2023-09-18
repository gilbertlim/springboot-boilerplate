package com.megazone.springbootboilerplate.common.event;

import org.springframework.context.ApplicationEventPublisher;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Events {
    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void publish(Object event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }
}
