package com.boilerplate.common.config.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.boilerplate.*.infrastructure.provider.feign")
public class OpenFeignConfig {

}
