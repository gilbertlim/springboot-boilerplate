package com.megazone.springbootboilerplate.shop.service.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.megazone.springbootboilerplate.shop.domain.Shop.SHOP_NAME_ERROR_MESSAGE;

@Constraint(validatedBy = {ShopNameValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShopName {

    String message() default SHOP_NAME_ERROR_MESSAGE;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
