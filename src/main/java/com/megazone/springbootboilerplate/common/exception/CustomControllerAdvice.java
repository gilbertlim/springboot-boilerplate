package com.megazone.springbootboilerplate.common.exception;

import com.megazone.springbootboilerplate.common.dto.Response;
import com.megazone.springbootboilerplate.common.dto.ResponseType;
import com.megazone.springbootboilerplate.shop.domain.exception.ShopTierException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomControllerAdvice {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<Void>> error(IllegalArgumentException e) {
        return Response.error(ResponseType.BAD_REQUEST, e);
    }

    @ExceptionHandler(ShopTierException.class)
    public ResponseEntity<Response<Void>> error(ShopTierException e) {
        return Response.of("T-1", e.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<Response<Void>> error(DuplicateDataException e) {
        return Response.error(ResponseType.DUPLICATE, e);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<Void>> error(NotFoundException e) {
        return Response.error(ResponseType.NOT_FOUND, e);
    }
}
