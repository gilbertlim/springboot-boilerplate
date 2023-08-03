package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.shop.domain.tier.Bronze;
import com.megazone.springbootboilerplate.shop.domain.tier.ShopTier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Shop {

    private Long id;
    private String name;
    private ShopTier tier;

    public Shop(String name) {
        this.name = name;
        this.tier = new Bronze();
    }

    public void upgrade() {
        tier = tier.up();
    }

    public void downgrade() {tier = tier.down();}
}
