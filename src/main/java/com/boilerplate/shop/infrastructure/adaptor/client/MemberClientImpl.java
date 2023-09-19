package com.boilerplate.shop.infrastructure.adaptor.client;

import org.springframework.stereotype.Component;
import com.boilerplate.shop.domain.port.client.MemberClient;
import com.boilerplate.shop.infrastructure.provider.feign.MemberFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MemberClientImpl implements MemberClient {

    private final MemberFeignClient memberFeignClient;

    public String getMember(int memberId) {
        return memberFeignClient.getMember(memberId);
    }

}
