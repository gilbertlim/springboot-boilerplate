package com.megazone.springbootboilerplate.shop.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ShopAddress {
    private String address;
    private String detailAddress;

    public ShopAddress(String address, String detailAddress) {
        validate(address, detailAddress);
        this.address = address;
        this.detailAddress = detailAddress;
    }

    private void validate(String address, String detailAddress) {
        if (!StringUtils.hasText(address) || !StringUtils.hasText(detailAddress)) {
            throw new IllegalArgumentException("기본 주소 또는 세부 주소 입력이 필요합니다.");
        }
    }
}
