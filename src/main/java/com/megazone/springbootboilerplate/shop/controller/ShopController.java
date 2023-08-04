package com.megazone.springbootboilerplate.shop.controller;

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

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShopController {
    private final ShopWriteService shopWriteService;
    private final ShopReadService shopReadService;

    @PostMapping("/shops")
    public ResponseEntity<ShopResponse> create(@RequestBody ShopCreateRequest request) {
        ShopResponse shopResponse = shopWriteService.create(request);
        return ResponseEntity.created(URI.create("/shops/" + shopResponse.id())).build();
    }

    @GetMapping("/shops")
    public ResponseEntity<List<ShopResponse>> findAll() {
        return ResponseEntity.ok().body(shopReadService.findAll());
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShopResponse> findOne(@PathVariable Long shopId) {
        return ResponseEntity.ok().body(shopReadService.findById(shopId));
    }

    @PutMapping("/shops/{shopId}:{shopAction}")
    public ResponseEntity<ShopResponse> update(@PathVariable Long shopId, @PathVariable String shopAction) {
        return ResponseEntity.ok().body(shopWriteService.update(shopId, shopAction));
    }

    @GetMapping("/shops/bo")
    public ResponseEntity<String> getBoData() {
        return ResponseEntity.ok().body(shopReadService.getBoData());
    }
}
