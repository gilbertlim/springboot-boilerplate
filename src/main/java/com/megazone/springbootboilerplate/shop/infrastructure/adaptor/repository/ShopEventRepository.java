package com.megazone.springbootboilerplate.shop.infrastructure.adaptor.repository;


import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import com.megazone.springbootboilerplate.shop.domain.event.ShopTierEvent;
import com.megazone.springbootboilerplate.shop.infrastructure.provider.mybatis.mapper.ShopEventMapper;

@RequiredArgsConstructor
@Repository
public class ShopEventRepository {

    private final ShopEventMapper shopEventMapper;

    public void save(ShopTierEvent shopMemberEvent) {
        shopEventMapper.save(shopMemberEvent);
    }
}
