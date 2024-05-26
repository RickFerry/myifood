package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class CidadeNaoEncontradaException extends RuntimeException {
    private final Long id;
    /**
     * Construtor que recebe a mensagem e o id da cidade
     */
    public CidadeNaoEncontradaException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
