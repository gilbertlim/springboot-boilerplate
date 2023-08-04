package com.megazone.springbootboilerplate.common.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "backoffice", url = "${feign.backoffice.base-url}")
public interface BackOfficeFeignClient {

    @GetMapping("/backoffice-inner-api")
    String getBackOfficeData();
}
