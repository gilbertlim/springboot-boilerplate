package com.megazone.springbootboilerplate.shop.infra.dao;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ShopDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("""
            INSERT INTO shop (shop_name)
            VALUES (#{name})
            """)
    void save(Shop shop);

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
    Optional<Shop> findById(@Param("id") Long id);
}
