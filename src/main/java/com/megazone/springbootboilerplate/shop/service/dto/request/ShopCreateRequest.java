package com.megazone.springbootboilerplate.shop.service.dto.request;

import com.megazone.springbootboilerplate.shop.service.dto.validator.ShopName;
import jakarta.validation.constraints.NotEmpty;

public record ShopCreateRequest(
    @ShopName
    String name,
    @NotEmpty
    String address,
    @NotEmpty
    String detailAddress

) {
}
