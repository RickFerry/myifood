package com.ferry.myifood.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EstadoNaoEncontradoException.class)
    public ResponseEntity<Throwable> handleEstadoNaoEncontradoException(EstadoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getRootCause(e));
    }

    @ExceptionHandler(CidadeNaoEncontradaException.class)
    public ResponseEntity<Throwable> handleCidadeNaoEncontradaException(CidadeNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionUtils.getRootCause(e));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;
    }
}
