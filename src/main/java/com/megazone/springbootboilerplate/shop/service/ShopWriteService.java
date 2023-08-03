package com.megazone.springbootboilerplate.shop.service;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.domain.ShopRepository;
import com.megazone.springbootboilerplate.shop.service.dto.request.ShopCreateRequest;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ShopWriteService {

    private final ShopRepository shopRepository;

    public ShopResponse create(ShopCreateRequest request) {
        Shop shop = new Shop(request.name());
        shopRepository.save(shop);
        return ShopResponse.of(shop);
    }

    public ShopResponse update(Long shopId, String shopAction) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        switch (shopAction) {
            case "upgrade" -> shop.upgrade();
            case "downgrade" -> shop.downgrade();
            default -> throw new IllegalArgumentException();
        }
        shopRepository.update(shop);
        return ShopResponse.of(shop);
    }
}
