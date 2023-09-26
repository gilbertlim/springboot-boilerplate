package com.boilerplate.shop.application.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boilerplate.common.event.Events;
import com.boilerplate.common.exception.DuplicateDataException;
import com.boilerplate.common.exception.NotFoundException;
import com.boilerplate.shop.application.data.dto.request.ShopCreateRequest;
import com.boilerplate.shop.application.data.dto.response.ShopResponse;
import com.boilerplate.shop.application.data.mapper.ShopFieldMapper;
import com.boilerplate.shop.domain.data.entity.Shop;
import com.boilerplate.shop.domain.event.ShopTierEvent;
import com.boilerplate.shop.domain.exception.ShopTierException;
import com.boilerplate.shop.domain.port.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

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

        ShopTierEvent event = ShopTierEvent.tierCreated(shop.getId());
        Events.publish(event);

        return ShopFieldMapper.INSTANCE.toShopResponse(shop);
    }

    @Cacheable(value = "shop", key = "#shopId")
    public ShopResponse update(Long shopId, String shopAction) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException(shopId + " Shop을 찾을 수 없습니다."));
        switch (shopAction) {
            case "upgrade" -> shop.upgrade();
            case "downgrade" -> shop.downgrade();
            default -> throw new ShopTierException(shopAction + " 요청이 올바르지 않습니다.");
        }
        shopRepository.update(shop);

        ShopTierEvent event = ShopTierEvent.tierChanged(shop.getId());
        Events.publish(event);

        return ShopFieldMapper.INSTANCE.toShopResponse(shop);
    }
}
