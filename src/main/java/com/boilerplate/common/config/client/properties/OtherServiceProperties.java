package com.boilerplate.common.config.client.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "other-service")
public record OtherServiceProperties(
    Map<String, ServiceProperties> services
) {

    public record ServiceProperties(
        String url
    ) {
    }
}
