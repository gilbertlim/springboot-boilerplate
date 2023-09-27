package com.boilerplate.shop.domain.data.entity;

import lombok.*;

import com.boilerplate.shop.domain.data.vo.ShopAddress;
import com.boilerplate.shop.domain.data.vo.ShopName;
import com.boilerplate.shop.domain.data.vo.type.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Shop {

    private Long id;
    private ShopName name;
    private ShopAddress address;
    private ShopTier tier; // 인터페이스 구현체 상태 패턴 적용
    private ShopTierType tierType; // ENUM 상태 패턴 적용

    public Shop(String name) {
        this(name, "", "");
    }

    public Shop(String name, String address, String detailAddress) {
        this.name = new ShopName(name);
        this.address = new ShopAddress(address, detailAddress);
        this.tier = new Bronze();
        this.tierType = ShopTierType.BRONZE;
    }

    public void upgrade() {
        tier = tier.up();
        tierType = tierType.up();
    }

    public void downgrade() {
        tier = tier.down();
        tierType = tierType.down();
    }
}
