package com.boilerplate.shop.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.boilerplate.common.dto.CommonResponse;
import com.boilerplate.common.dto.CommonResponseType;
import com.boilerplate.shop.application.data.dto.request.ShopCreateRequest;
import com.boilerplate.shop.application.data.dto.response.ShopResponse;
import com.boilerplate.shop.application.service.ShopReadService;
import com.boilerplate.shop.application.service.ShopWriteService;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopWriteService shopWriteService;
    private final ShopReadService shopReadService;

    @PostMapping("/shops")
    public ResponseEntity<CommonResponse<ShopResponse>> create(@RequestBody @Validated ShopCreateRequest request) {
        ShopResponse shopResponse = shopWriteService.create(request);
        return CommonResponse.created(shopResponse, "/shops/" + shopResponse.id());
    }

    @GetMapping("/shops")
    public ResponseEntity<CommonResponse<List<ShopResponse>>> findAll() {
        return CommonResponse.ok(shopReadService.findAll());
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<CommonResponse<ShopResponse>> findOne(@PathVariable Long shopId) {
        return CommonResponse.ok(shopReadService.findById(shopId));
    }

    @PutMapping("/shops/{shopId}:{shopAction}")
    public ResponseEntity<CommonResponse<ShopResponse>> update(@PathVariable Long shopId, @PathVariable String shopAction) {
        return CommonResponse.withData(shopWriteService.update(shopId, shopAction), CommonResponseType.UPDATE);
    }
}
