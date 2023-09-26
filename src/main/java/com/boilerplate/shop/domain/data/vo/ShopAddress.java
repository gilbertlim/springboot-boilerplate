package com.boilerplate.shop.domain.data.vo;

import lombok.*;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ShopAddress {

    private String address;
    private String detailAddress;

    public ShopAddress(String address, String detailAddress) {
        this.address = address;
        this.detailAddress = detailAddress;
    }
}
