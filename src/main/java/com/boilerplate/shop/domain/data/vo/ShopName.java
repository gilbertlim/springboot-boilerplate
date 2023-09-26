package com.boilerplate.shop.domain.data.vo;

import com.boilerplate.shop.domain.exception.ShopNameException;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ShopName {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 15;
    public static final String EX_MESSAGE = String.format("길이는 %d이상 %d이하이어야 합니다.", MIN_LENGTH, MAX_LENGTH);

    private String value;

    public ShopName(String name) {
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new ShopNameException(EX_MESSAGE);
        }
        this.value = name;
    }
}
