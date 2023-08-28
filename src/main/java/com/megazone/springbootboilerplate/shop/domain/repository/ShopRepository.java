package com.megazone.springbootboilerplate.shop.domain.repository;


import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopRepository {

    Shop save(Shop shop);

    List<Shop> findAll();

    Optional<Shop> findById(Long id);

    Optional<ShopWithReviewsQuery> findShopReviewsById(Long id);

    Optional<Shop> findByName(String name);

    void update(Shop shop);
}
