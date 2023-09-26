package com.boilerplate.shop.application.data.dto.request;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.boilerplate.common.utils.ValidationUtils;
import com.boilerplate.shop.domain.ShopName;

public class ShopNameValidator implements ConstraintValidator<ShopNameLength, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(ShopName.EX_MESSAGE).addConstraintViolation();
        return ValidationUtils.isValidConstructor(() -> new ShopName(value));
    }
}
