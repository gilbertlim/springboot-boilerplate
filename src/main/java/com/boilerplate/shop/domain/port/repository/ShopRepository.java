package com.boilerplate.shop.domain.port.repository;


import java.util.List;
import java.util.Optional;

import com.boilerplate.shop.domain.data.dto.ShopWithReviewsQuery;
import com.boilerplate.shop.domain.data.entity.Shop;

public interface ShopRepository {

    Shop save(Shop shop);

    List<Shop> findAll();

    Optional<Shop> findById(Long id);

    Optional<ShopWithReviewsQuery> findShopReviewsById(Long id);

    Optional<Shop> findByName(String name);

    void update(Shop shop);
}
