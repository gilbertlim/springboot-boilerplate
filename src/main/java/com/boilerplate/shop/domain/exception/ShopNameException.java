package com.boilerplate.shop.domain.exception;

import com.boilerplate.common.exception.InvalidValueException;

public class ShopNameException extends InvalidValueException {

    public ShopNameException(String message) {
        super(message);
    }
}
