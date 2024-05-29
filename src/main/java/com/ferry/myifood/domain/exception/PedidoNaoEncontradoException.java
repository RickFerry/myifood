package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class PedidoNaoEncontradoException extends RuntimeException{
    private final Long id;

    public PedidoNaoEncontradoException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
