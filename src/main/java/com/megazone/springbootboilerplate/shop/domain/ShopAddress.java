package com.megazone.springbootboilerplate.shop.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
