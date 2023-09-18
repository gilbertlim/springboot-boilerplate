package com.megazone.springbootboilerplate.shop.infrastructure.provider.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.Shop;

@Mapper
public interface ShopMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("""
        insert into shop (shop_name, shop_address, shop_detail_address, shop_tier, shop_tier_code)
        values (#{name.value}, #{address.address}, #{address.detailAddress}, #{tier}, #{tierType})
        """)
    void save(Shop shop);

    @Update("""
        update shop
        set shop_name = #{name.value}, shop_tier = #{tier}, shop_tier_code = #{tierType}
        where shop_id = #{id}
        """)
    void update(Shop shop);

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

    @ResultMap("shopRowMapper")
    @Select("""
        select *
        from shop
        where shop_name = #{name}
        """)
    Shop findByName(@Param("name") String name);

    ShopWithReviewsQuery findShopReviewsById(Long id);
}
