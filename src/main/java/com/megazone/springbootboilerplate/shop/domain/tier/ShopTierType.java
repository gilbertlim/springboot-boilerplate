package com.megazone.springbootboilerplate.shop.domain.tier;

import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;

public enum ShopTierType implements ShopTierSpec {
    BRONZE(
        new ShopTierSpec() {
            @Override
            public ShopTierType up() {
                return SILVER;
            }

            @Override
            public ShopTierType down() {
                throw new ShopTierException("등급을 더 이상 내릴 수 없습니다.");
            }
        }
    ), SILVER(
        new ShopTierSpec() {
            @Override
            public ShopTierType up() {
                return GOLD;
            }

            @Override
            public ShopTierType down() {
                return BRONZE;
            }
        }
    ), GOLD(
        new ShopTierSpec() {
            @Override
            public ShopTierType up() {
                throw new ShopTierException("등급을 더 이상 올릴 수 없습니다.");
            }

            @Override
            public ShopTierType down() {
                return SILVER;
            }
        }
    );


    private final ShopTierSpec status;

    ShopTierType(ShopTierSpec status) {
        this.status = status;
    }

    @Override
    public ShopTierType up() {
        return status.up();
    }

    @Override
    public ShopTierType down() {
        return status.down();
    }
}
