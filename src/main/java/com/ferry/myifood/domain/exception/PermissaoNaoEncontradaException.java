package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class PermissaoNaoEncontradaException extends RuntimeException {
    private final Long id;

    public PermissaoNaoEncontradaException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
