package com.boilerplate.shop.domain.tier;

public interface ShopTier {

    ShopTier up();

    ShopTier down();

    String getCode();
}
