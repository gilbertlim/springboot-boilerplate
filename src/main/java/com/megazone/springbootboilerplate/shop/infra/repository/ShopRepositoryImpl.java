package com.megazone.springbootboilerplate.shop.infra.repository;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.domain.ShopRepository;
import com.megazone.springbootboilerplate.shop.infra.dao.ShopDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ShopRepositoryImpl implements ShopRepository {
    private final ShopDao shopDao;

    @Override
    public Shop save(Shop shop) {
        shopDao.save(shop);
        return shop;
    }

    @Override
    public List<Shop> findAll() {
        return shopDao.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return shopDao.findById(id);
    }

    @Override
    public void update(Shop shop) {
        shopDao.update(shop);
    }
}
