package com.megazone.springbootboilerplate.shop.service.dto.response;

import com.megazone.springbootboilerplate.shop.domain.Shop;

public record ShopResponse(
        Long id,
        String name
) {
    public static ShopResponse of(Shop shop) {
        return new ShopResponse(shop.getId(), shop.getName());
    }
}
