package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class FormasPagamentoNaoEncontradaException extends RuntimeException {
    private final Long id;

    public FormasPagamentoNaoEncontradaException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
