package com.ferry.myifood.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorsHandler {
    private final MessageSource messageSource;

    public ErrorsHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(EstadoNaoEncontradoException.class)
    public ResponseEntity<Throwable> handleEstadoNaoEncontradoException(EstadoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionUtils.getRootCause(e));
    }

    @ExceptionHandler(CidadeNaoEncontradaException.class)
    public ResponseEntity<Throwable> handleCidadeNaoEncontradaException(CidadeNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionUtils.getRootCause(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getBindingResult().getFieldErrors().stream().map(error ->
                new Error(
                        error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())
                )).collect(Collectors.toList()));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;
    }
}
