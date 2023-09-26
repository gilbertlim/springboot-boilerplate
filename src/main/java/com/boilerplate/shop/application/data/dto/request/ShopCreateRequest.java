package com.boilerplate.shop.application.data.dto.request;

import jakarta.validation.constraints.NotEmpty;

import com.boilerplate.common.validation.NonSpecialCharacters;

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
