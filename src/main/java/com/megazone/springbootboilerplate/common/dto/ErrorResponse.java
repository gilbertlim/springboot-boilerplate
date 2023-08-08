package com.megazone.springbootboilerplate.common.dto;

import org.springframework.validation.FieldError;

public record ErrorResponse(
    String field,
    String message,
    String inValidValue
) {

    public static ErrorResponse of(FieldError error) {
        return new ErrorResponse(error.getField(), error.getDefaultMessage(), (String) error.getRejectedValue());
    }
}
