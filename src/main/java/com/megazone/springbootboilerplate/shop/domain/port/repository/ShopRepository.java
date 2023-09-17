package com.megazone.springbootboilerplate.shop.domain.port.repository;


import java.util.List;
import java.util.Optional;

import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.Shop;

public interface ShopRepository {

    List<Shop> findAll();

    Optional<Shop> findById(Long id);

    Optional<ShopWithReviewsQuery> findShopReviewsById(Long id);
}
