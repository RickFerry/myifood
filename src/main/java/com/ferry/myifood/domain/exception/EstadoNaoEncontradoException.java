package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class EstadoNaoEncontradoException extends RuntimeException {
    private final Long id;
    /**
     * Construtor que recebe a mensagem e o id do estado
     */
    public EstadoNaoEncontradoException(final Long id, final String message) {
        super(message);
        this.id = id;
    }
}
