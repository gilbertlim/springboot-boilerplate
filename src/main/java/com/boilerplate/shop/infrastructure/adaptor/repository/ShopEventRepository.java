package com.boilerplate.shop.infrastructure.adaptor.repository;


import org.springframework.stereotype.Repository;
import com.boilerplate.shop.domain.event.ShopTierEvent;
import com.boilerplate.shop.infrastructure.provider.mybatis.mapper.ShopEventMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ShopEventRepository {

    private final ShopEventMapper shopEventMapper;

    public void save(ShopTierEvent shopMemberEvent) {
        shopEventMapper.save(shopMemberEvent);
    }
}
