package com.boilerplate.shop.presentation.exhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.boilerplate.common.dto.CommonResponse;
import com.boilerplate.shop.domain.exception.ShopTierException;

@RestControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(ShopTierException.class)
    public ResponseEntity<CommonResponse<Void>> error(ShopTierException e) {
        return CommonResponse.of("T-1", e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

}
