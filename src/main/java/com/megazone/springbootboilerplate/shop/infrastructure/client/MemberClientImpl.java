package com.megazone.springbootboilerplate.shop.infrastructure.client;

import com.megazone.springbootboilerplate.shop.domain.client.MemberClient;
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
