package com.boilerplate.shop.infrastructure.provider.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member")
public interface MemberFeignClient {

    @GetMapping(path = "/members/${memberId}")
    String getMember(@PathVariable("memberId") int memberId);
}
