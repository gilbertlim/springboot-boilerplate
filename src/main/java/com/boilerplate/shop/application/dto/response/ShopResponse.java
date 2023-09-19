package com.boilerplate.shop.application.dto.response;

import com.boilerplate.shop.domain.Shop;

public record ShopResponse(
    Long id,
    String name,
    String address,
    String detailAddress,
    String tier
) {

    public static ShopResponse of(Shop shop) {
        return new ShopResponse(shop.getId(),
            shop.getName().getValue(),
            shop.getAddress().getAddress(),
            shop.getAddress().getDetailAddress(),
            shop.getTier().getCode());
    }
}
