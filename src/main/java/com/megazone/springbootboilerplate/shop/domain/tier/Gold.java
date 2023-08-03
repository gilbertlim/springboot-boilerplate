package com.megazone.springbootboilerplate.shop.domain.tier;

public class Gold implements ShopTier {
    @Override
    public ShopTier up() {
        throw new IllegalStateException();
    }

    @Override
    public ShopTier down() {
        return new Silver();
    }

    @Override
    public String getCode() {
        return "gold";
    }
}
