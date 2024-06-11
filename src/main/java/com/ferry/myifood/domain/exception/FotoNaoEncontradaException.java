package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class FotoNaoEncontradaException extends RuntimeException {
    private final Long id;

    public FotoNaoEncontradaException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
