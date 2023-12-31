package com.boilerplate.shop.domain.data.vo.type;

public class Silver implements ShopTier {

    @Override
    public ShopTier up() {
        return new Gold();
    }

    @Override
    public ShopTier down() {
        return new Bronze();
    }

    @Override
    public String getCode() {
        return "Silver";
    }
}
