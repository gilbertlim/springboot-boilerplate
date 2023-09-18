package com.megazone.springbootboilerplate.shop.application.dto.request;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.megazone.springbootboilerplate.common.utils.ValidationUtils;

public class ShopNameValidator implements ConstraintValidator<ShopNameLength, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(com.megazone.springbootboilerplate.shop.domain.ShopName.EX_MESSAGE).addConstraintViolation();
        return ValidationUtils.isValidConstructor(() -> new com.megazone.springbootboilerplate.shop.domain.ShopName(value));
    }
}
