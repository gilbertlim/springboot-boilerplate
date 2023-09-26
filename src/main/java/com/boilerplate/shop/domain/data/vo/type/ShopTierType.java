package com.boilerplate.shop.domain.data.vo.type;

import com.boilerplate.shop.domain.exception.ShopTierException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShopTierType {
    BRONZE("B") {
        public ShopTierType up() {
            return SILVER;
        }

        public ShopTierType down() {
            throw new ShopTierException("등급을 더 이상 내릴 수 없습니다.");
        }
    },

    SILVER("S") {
        public ShopTierType up() { return GOLD; }

        public ShopTierType down() {
            return BRONZE;
        }
    },

    GOLD("G") {
        public ShopTierType up() {
            throw new ShopTierException("등급을 더 이상 올릴 수 없습니다.");
        }

        public ShopTierType down() {
            return SILVER;
        }
    };

    private final String code;

    public abstract ShopTierType up();

    public abstract ShopTierType down();
}
