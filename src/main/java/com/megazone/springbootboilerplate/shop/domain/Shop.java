package com.megazone.springbootboilerplate.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Shop {

    private Long id;
    private String name;

    public Shop(String name) {
        this.name = name;
    }
}
