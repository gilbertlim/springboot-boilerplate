package com.megazone.springbootboilerplate.common.config.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.megazone.springbootboilerplate.*.infrastructure.provider.feign")
public class OpenFeignConfig {

}
