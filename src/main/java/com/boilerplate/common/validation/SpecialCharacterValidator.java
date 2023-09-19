package com.boilerplate.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements ConstraintValidator<NonSpecialCharacters, String> {

    private static final Pattern PATTERN = Pattern.compile("[가-힣0-9a-zA-Z\\-\\s]*");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return PATTERN.matcher(value).matches();
    }
}
