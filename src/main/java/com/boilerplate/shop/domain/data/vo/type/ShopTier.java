package com.boilerplate.shop.domain.data.vo.type;

public interface ShopTier {

    ShopTier up();

    ShopTier down();

    String getCode();
}
