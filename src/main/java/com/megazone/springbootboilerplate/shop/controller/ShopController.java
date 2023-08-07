package com.megazone.springbootboilerplate.shop.controller;

import com.megazone.springbootboilerplate.common.dto.Response;
import com.megazone.springbootboilerplate.common.dto.ResponseType;
import com.megazone.springbootboilerplate.shop.service.ShopReadService;
import com.megazone.springbootboilerplate.shop.service.ShopWriteService;
import com.megazone.springbootboilerplate.shop.service.dto.request.ShopCreateRequest;
import com.megazone.springbootboilerplate.shop.service.dto.response.ShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopController {
    private final ShopWriteService shopWriteService;
    private final ShopReadService shopReadService;

    @PostMapping("/shops")
    public ResponseEntity<Response<ShopResponse>> create(@RequestBody ShopCreateRequest request) {
        ShopResponse shopResponse = shopWriteService.create(request);
        return Response.created(ResponseType.CREATE, shopResponse, "/shops/" + shopResponse.id());
    }

    @GetMapping("/shops")
    public ResponseEntity<Response<List<ShopResponse>>> findAll() {
        return Response.withData(ResponseType.OK, shopReadService.findAll());
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<Response<ShopResponse>> findOne(@PathVariable Long shopId) {
        return Response.withData(ResponseType.OK, shopReadService.findById(shopId));
    }

    @PutMapping("/shops/{shopId}:{shopAction}")
    public ResponseEntity<Response<ShopResponse>> update(@PathVariable Long shopId, @PathVariable String shopAction) {
        return Response.withData(ResponseType.UPDATE, shopWriteService.update(shopId, shopAction));
    }
}
