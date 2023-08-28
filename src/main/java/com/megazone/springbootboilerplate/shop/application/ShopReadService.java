package com.megazone.springbootboilerplate.shop.application;

import com.megazone.springbootboilerplate.common.exception.NotFoundException;
import com.megazone.springbootboilerplate.shop.application.dto.response.ShopResponse;
import com.megazone.springbootboilerplate.shop.application.dto.response.query.ShopWithReviewsQuery;
import com.megazone.springbootboilerplate.shop.domain.dip.client.MemberClient;
import com.megazone.springbootboilerplate.shop.domain.dip.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public ShopResponse findById(Long id) {
        return shopRepository.findById(id)
            .map(ShopResponse::of)
            .orElseThrow(() -> new NotFoundException(id + " Shop을 찾을 수 없습니다."));
    }

    public ShopWithReviewsQuery findShopWithReviewsById(Long id) {
        return shopRepository.findShopReviewsById(id)
            .orElseThrow(() -> new NotFoundException(id + " Shop을 찾을 수 없습니다."));
    }

    public Optional<String> getMember(int memberId) {
        return Optional.ofNullable(memberClient.getMember(memberId));
    }
}
