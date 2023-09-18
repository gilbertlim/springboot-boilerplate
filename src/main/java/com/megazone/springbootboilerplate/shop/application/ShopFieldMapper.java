package com.megazone.springbootboilerplate.shop.application;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import com.megazone.springbootboilerplate.shop.application.dto.response.ShopResponse;
import com.megazone.springbootboilerplate.shop.domain.Shop;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopFieldMapper {

    ShopFieldMapper INSTANCE = Mappers.getMapper(ShopFieldMapper.class);

    @Mapping(source = "address.address", target = "address")
    @Mapping(source = "address.detailAddress", target = "detailAddress")
    @Mapping(source = "tier.code", target = "tier")
    @Mapping(source = "name.value", target = "name")
    ShopResponse toShopResponse(Shop shop);
}
