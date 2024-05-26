package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class ProdutoNaoEncontradoException extends RuntimeException {
    private final Long id;

    public ProdutoNaoEncontradoException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
