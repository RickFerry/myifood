package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class CozinhaNaoEncontradaException extends RuntimeException {
    private final Long id;

    public CozinhaNaoEncontradaException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
