package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.common.event.Events;
import com.megazone.springbootboilerplate.shop.domain.tier.Bronze;
import com.megazone.springbootboilerplate.shop.domain.tier.ShopTier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Shop {

    private static final int SHOP_NAME_MIN_LENGTH = 2;
    private static final int SHOP_NAME_MAX_LENGTH = 10;
    public static final String SHOP_NAME_ERROR_MESSAGE = SHOP_NAME_MIN_LENGTH+ "글자 이상 " + SHOP_NAME_MAX_LENGTH + "글자 이하이어야 합니다.";

    private Long id;
    private String name;
    private ShopTier tier;

    public Shop(String name) {
        validateName(name);
        this.name = name;
        this.tier = new Bronze();
    }

    public static void validateName(String name) {
        if (name == null || name.length() < SHOP_NAME_MIN_LENGTH || name.length() > SHOP_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(SHOP_NAME_ERROR_MESSAGE);
        }
    }

    public void upgrade() {
        tier = tier.up();
        Events.raise(new ShopTierEvent(id, "upgrade"));
    }

    public void downgrade() {
        tier = tier.down();
        Events.raise(new ShopTierEvent(id, "downgrade"));
    }
}
