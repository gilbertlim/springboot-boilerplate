package com.megazone.springbootboilerplate.shop.application.dto.request;

import jakarta.validation.constraints.NotEmpty;

import com.megazone.springbootboilerplate.common.validation.NonSpecialCharacters;

public record ShopCreateRequest(
    @ShopNameLength
    String name,

    @NotEmpty
    @NonSpecialCharacters
    String address,

    @NotEmpty
    @NonSpecialCharacters
    String detailAddress
) {
}
