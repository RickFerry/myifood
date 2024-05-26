package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class GrupoNaoEncontradoException extends RuntimeException {
    private final Long id;

    public GrupoNaoEncontradoException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
