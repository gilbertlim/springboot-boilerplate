package com.megazone.springbootboilerplate.shop.service;

import com.megazone.springbootboilerplate.shop.domain.ShopRepository;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public ShopResponse findById(Long id) {
        return shopRepository.findById(id)
                .map(ShopResponse::of)
                .orElseThrow();
    }
}
