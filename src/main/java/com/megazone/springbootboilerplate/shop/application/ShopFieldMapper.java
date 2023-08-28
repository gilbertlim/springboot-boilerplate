package com.megazone.springbootboilerplate.shop.application;

import com.megazone.springbootboilerplate.shop.application.dto.response.ShopResponse;
import com.megazone.springbootboilerplate.shop.domain.Shop;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopFieldMapper {

    ShopFieldMapper INSTANCE = Mappers.getMapper(ShopFieldMapper.class);

    @Mapping(source = "shop.address.address", target = "address")
    @Mapping(source = "shop.address.detailAddress", target = "detailAddress")
    @Mapping(source = "shop.tier.code", target = "tier")
    ShopResponse toShopResponse(Shop shop);
}
