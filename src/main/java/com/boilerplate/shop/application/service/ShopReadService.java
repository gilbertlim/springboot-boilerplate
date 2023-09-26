package com.boilerplate.shop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boilerplate.common.exception.NotFoundException;
import com.boilerplate.shop.application.data.dto.response.ShopResponse;
import com.boilerplate.shop.application.data.dto.response.query.ShopWithReviewsQuery;
import com.boilerplate.shop.domain.port.client.MemberClient;
import com.boilerplate.shop.domain.port.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopReadService {

    private final ShopRepository shopRepository;
    private final MemberClient memberClient;

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

    public Optional<String> getMember(int memberId) {
        return Optional.ofNullable(memberClient.getMember(memberId));
    }
}
