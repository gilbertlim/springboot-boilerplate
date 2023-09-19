package com.boilerplate.shop.application;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boilerplate.common.exception.NotFoundException;
import com.boilerplate.shop.application.dto.response.ShopResponse;
import com.boilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.boilerplate.shop.domain.port.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopReadService {

    private final ShopRepository shopRepository;

    public List<ShopResponse> findAll() {
        return shopRepository.findAll()
            .stream()
            .map(ShopResponse::of)
            .toList();
    }

    @Cacheable(value = "shop", key = "#shopId")
    public ShopResponse findById(Long shopId) {
        return shopRepository.findById(shopId)
            .map(ShopResponse::of)
            .orElseThrow(() -> new NotFoundException(shopId + " Shop을 찾을 수 없습니다."));
    }

    public ShopWithReviewsQuery findShopWithReviewsById(Long id) {
        return shopRepository.findShopReviewsById(id)
            .orElseThrow(() -> new NotFoundException(id + " Shop을 찾을 수 없습니다."));
    }

}
