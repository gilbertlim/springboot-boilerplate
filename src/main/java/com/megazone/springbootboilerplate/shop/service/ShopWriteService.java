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
}
