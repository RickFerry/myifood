package com.ferry.myifood.domain.exception;

public class EstadoNaoEncontradoException extends RuntimeException {
    /**
     * @param message
     */
    public EstadoNaoEncontradoException(final String message) {
        super(message);
    }
}
