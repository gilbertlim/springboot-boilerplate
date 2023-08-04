package com.megazone.springbootboilerplate.config.web;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.megazone.springbootboilerplate.shop.infra.client")
public class OpenFeignConfig {

}
