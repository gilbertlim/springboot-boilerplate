package com.megazone.springbootboilerplate.shop.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "bo", url = "${feign.bo.base-url}")
public interface ShopFeignClient {

    @GetMapping("/bo")
    String getData();
}
