package com.boilerplate.shop.infrastructure.adaptor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import com.boilerplate.shop.domain.data.dto.ShopWithReviewsQuery;
import com.boilerplate.shop.domain.data.entity.Shop;
import com.boilerplate.shop.domain.port.repository.ShopRepository;
import com.boilerplate.shop.infrastructure.provider.mybatis.mapper.ShopMapper;

@RequiredArgsConstructor
@Repository
public class ShopRepositoryImpl implements ShopRepository {

    private final ShopMapper shopMapper;

    @Override
    public Shop save(Shop shop) {
        shopMapper.save(shop);
        return shop;
    }

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

    @Override
    public Optional<Shop> findByName(String name) {
        return Optional.ofNullable(shopMapper.findByName(name));
    }

    @Override
    public void update(Shop shop) {
        shopMapper.update(shop);
    }
}
