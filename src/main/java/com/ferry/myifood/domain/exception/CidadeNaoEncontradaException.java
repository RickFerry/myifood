package com.ferry.myifood.domain.exception;

public class CidadeNaoEncontradaException extends RuntimeException {
    public CidadeNaoEncontradaException(String message) {
        super(message);
    }
}
