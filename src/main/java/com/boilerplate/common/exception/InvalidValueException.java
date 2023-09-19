package com.boilerplate.common.exception;

public class InvalidValueException extends NoStackTraceException {

    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
