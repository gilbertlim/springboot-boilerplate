package com.megazone.springbootboilerplate.shop.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.megazone.springbootboilerplate.common.dto.CommonResponse;
import com.megazone.springbootboilerplate.shop.application.ShopReadService;
import com.megazone.springbootboilerplate.shop.application.dto.response.ShopResponse;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopReadService shopReadService;

    @GetMapping("/shops")
    public ResponseEntity<CommonResponse<List<ShopResponse>>> findAll() {
        return CommonResponse.ok(shopReadService.findAll());
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<CommonResponse<ShopResponse>> findOne(@PathVariable Long shopId) {
        return CommonResponse.ok(shopReadService.findById(shopId));
    }

}
