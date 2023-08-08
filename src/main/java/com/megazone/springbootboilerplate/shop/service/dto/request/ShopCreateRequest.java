package com.megazone.springbootboilerplate.shop.service.dto.request;

import com.megazone.springbootboilerplate.shop.service.dto.validator.ShopName;

public record ShopCreateRequest(
    @ShopName
    String name
) {
}
