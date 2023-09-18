package com.megazone.springbootboilerplate.shop.application;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.megazone.springbootboilerplate.common.exception.DuplicateDataException;
import com.megazone.springbootboilerplate.common.exception.NotFoundException;
import com.megazone.springbootboilerplate.shop.application.dto.request.ShopCreateRequest;
import com.megazone.springbootboilerplate.shop.application.dto.response.ShopResponse;
import com.megazone.springbootboilerplate.shop.domain.Shop;
import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;
import com.megazone.springbootboilerplate.shop.domain.port.repository.ShopRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class ShopWriteService {

    private final ShopRepository shopRepository;

    public ShopResponse create(ShopCreateRequest request) {
        shopRepository.findByName(request.name())
            .ifPresent(it -> {
                throw new DuplicateDataException("'" + request.name() + "'는 중복된 이름입니다.");
            });

        Shop shop = new Shop(request.name(), request.address(), request.detailAddress());
        shopRepository.save(shop);

        return ShopFieldMapper.INSTANCE.toShopResponse(shop);
    }

    @CachePut(value = "shop", key = "#shopId")
    public ShopResponse update(Long shopId, String shopAction) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(shopId + " Shop을 찾을 수 없습니다."));
        switch (shopAction) {
            case "upgrade" -> shop.upgrade();
            case "downgrade" -> shop.downgrade();
            default -> throw new ShopTierException(shopAction + " 요청이 올바르지 않습니다.");
        }
        shopRepository.update(shop);

        return ShopFieldMapper.INSTANCE.toShopResponse(shop);
    }
}
