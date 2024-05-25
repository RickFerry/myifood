package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class UniqueConstraintViolationException extends RuntimeException {
    private final String fieldName;

    public UniqueConstraintViolationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
