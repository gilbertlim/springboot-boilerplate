package com.megazone.springbootboilerplate.shop.domain;


import java.util.List;
import java.util.Optional;

public interface ShopRepository {
    Shop save(Shop shop);

    List<Shop> findAll();

    Optional<Shop> findById(Long id);

    void update(Shop shop);
}
