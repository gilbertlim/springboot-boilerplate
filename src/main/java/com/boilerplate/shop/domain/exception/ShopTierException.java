package com.boilerplate.shop.domain.exception;

import com.boilerplate.common.exception.NoStackTraceException;

public class ShopTierException extends NoStackTraceException {

    public ShopTierException(String message) {
        super(message);
    }
}
