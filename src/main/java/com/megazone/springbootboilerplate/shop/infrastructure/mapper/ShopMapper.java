package com.megazone.springbootboilerplate.shop.infrastructure.mapper;

import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShopMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("""
        INSERT INTO shop (shop_name, shop_address, shop_detail_address, shop_tier, shop_tier_code)
        VALUES (#{name}, #{address.address}, #{address.detailAddress}, #{tier}, #{tierType})
        """)
    void save(Shop shop);

    @Update("""
        UPDATE shop 
        SET shop_name = #{name}, shop_tier = #{tier}, shop_tier_code = #{tierType}
        WHERE shop_id = #{id}
        """)
    void update(Shop shop);

    @ResultMap("shopRowMapper")
    @Select("""
        SELECT * 
        FROM shop
        """)
    List<Shop> findAll();

    @ResultMap("shopRowMapper")
    @Select("""
        SELECT * 
        FROM shop
        WHERE shop_id = #{id}
        """)
    Shop findById(@Param("id") Long id);

    @ResultMap("shopRowMapper")
    @Select("""
        SELECT * 
        FROM shop
        WHERE shop_name = #{name}
        """)
    Shop findByName(@Param("name") String name);

    ShopWithReviewsQuery findShopReviewsById(Long id);
}
