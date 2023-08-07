package com.megazone.springbootboilerplate.common.exception;

public abstract class NoStackTraceException extends RuntimeException {

    protected NoStackTraceException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
