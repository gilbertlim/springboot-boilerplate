package com.megazone.springbootboilerplate.shop.infra.mapper;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShopMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("""
            INSERT INTO shop (shop_name, shop_tier, shop_address, shop_detail_address)
            VALUES (#{name}, #{tier}, #{address.address}, #{address.detailAddress})
            """)
    void save(Shop shop);

    @Update("""
            UPDATE shop 
            SET shop_name = #{name}, shop_tier = #{tier}
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
}
