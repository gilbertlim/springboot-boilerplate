package com.megazone.springbootboilerplate.shop.service.dto.validator;

import com.megazone.springbootboilerplate.shop.domain.Shop;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ShopNameValidator implements ConstraintValidator<ShopName, String> {

    @Override
    public void initialize(ShopName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Shop.validateName(value);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
