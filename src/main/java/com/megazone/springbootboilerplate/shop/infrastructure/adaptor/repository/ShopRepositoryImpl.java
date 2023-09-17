package com.megazone.springbootboilerplate.shop.infrastructure.adaptor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.domain.port.repository.ShopRepository;
import com.megazone.springbootboilerplate.shop.infrastructure.provider.mybatis.mapper.ShopMapper;

@RequiredArgsConstructor
@Repository
public class ShopRepositoryImpl implements ShopRepository {

    private final ShopMapper shopMapper;

    @Override
    public List<Shop> findAll() {
        return shopMapper.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return Optional.ofNullable(shopMapper.findById(id));
    }

    @Override
    public Optional<ShopWithReviewsQuery> findShopReviewsById(Long id) {
        return Optional.ofNullable(shopMapper.findShopReviewsById(id));
    }
}
