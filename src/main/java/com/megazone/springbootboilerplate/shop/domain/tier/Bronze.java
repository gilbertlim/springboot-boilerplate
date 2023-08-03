package com.megazone.springbootboilerplate.shop.domain.tier;

public class Bronze implements ShopTier {
    @Override
    public ShopTier up() {
        return new Silver();
    }

    @Override
    public ShopTier down() {
        throw new IllegalStateException();
    }

    @Override
    public String getCode() {
        return "bronze";
    }
}
