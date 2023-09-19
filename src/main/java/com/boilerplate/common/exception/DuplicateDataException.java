package com.boilerplate.common.exception;

public class DuplicateDataException extends NoStackTraceException {

    public DuplicateDataException(String message) {
        super(message);
    }
}
