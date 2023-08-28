package com.megazone.springbootboilerplate.shop.infrastructure.dip.client;

import com.megazone.springbootboilerplate.shop.domain.dip.client.MemberClient;
import com.megazone.springbootboilerplate.shop.infrastructure.provider.feign.MemberFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberClientImpl implements MemberClient {

    private final MemberFeignClient memberFeignClient;

    public String getMember(int memberId) {
        return memberFeignClient.getMember(memberId);
    }

}
