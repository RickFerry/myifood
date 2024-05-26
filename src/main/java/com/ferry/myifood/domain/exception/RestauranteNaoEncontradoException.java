package com.ferry.myifood.domain.exception;

import lombok.Getter;

@Getter
public class RestauranteNaoEncontradoException extends RuntimeException {
    private final Long id;

    public RestauranteNaoEncontradoException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
