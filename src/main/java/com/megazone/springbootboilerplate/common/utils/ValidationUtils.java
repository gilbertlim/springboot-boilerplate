package com.megazone.springbootboilerplate.common.utils;

import java.util.function.Supplier;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {
    public static boolean isValidConstructor(Supplier<Object> constructor) {
        try {
            constructor.get();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
