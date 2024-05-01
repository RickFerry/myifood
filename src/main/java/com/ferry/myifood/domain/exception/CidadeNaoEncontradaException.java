package com.ferry.myifood.domain.exception;

public class CidadeNaoEncontradaException extends RuntimeException {
    /**
     * @param message
     */
    public CidadeNaoEncontradaException(final String message) {
        super(message);
    }
}
