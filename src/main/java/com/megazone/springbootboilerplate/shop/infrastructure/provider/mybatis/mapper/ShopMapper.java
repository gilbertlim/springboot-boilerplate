package com.megazone.springbootboilerplate.shop.infrastructure.provider.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.Shop;

@Mapper
public interface ShopMapper {

    @ResultMap("shopRowMapper")
    @Select("""
        select *
        from shop
        """)
    List<Shop> findAll();

    @ResultMap("shopRowMapper")
    @Select("""
        select *
        from shop
        where shop_id = #{id}
        """)
    Shop findById(@Param("id") Long id);

    ShopWithReviewsQuery findShopReviewsById(Long id);
}
