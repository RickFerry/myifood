package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class UsuarioNaoEncontradoException extends RuntimeException {
    private final Long id;

    public UsuarioNaoEncontradoException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
