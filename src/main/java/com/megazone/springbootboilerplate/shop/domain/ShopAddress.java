package com.megazone.springbootboilerplate.shop.domain;

import com.megazone.springbootboilerplate.common.validation.SpecialCharacterValidator;
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
        SpecialCharacterValidator validator = new SpecialCharacterValidator();
        validator.validate(address);
        validator.validate(detailAddress);
        this.address = address;
        this.detailAddress = detailAddress;
    }
}
