package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class StatusInvalidoException extends RuntimeException {
    private final String name;
    public StatusInvalidoException(String name, String message) {
        super(message);
        this.name = name;
    }
}
