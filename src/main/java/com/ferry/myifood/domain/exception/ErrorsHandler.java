package com.ferry.myifood.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

@ControllerAdvice
public class ErrorsHandler {
    /**
     *
     */
    private final MessageSource messageSource;

    /**
     * @param messageSource
     */
    public ErrorsHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public final ResponseEntity<Error> handleUniqueConstraintViolationException(
            final UniqueConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        e.getFieldName(),
                        e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(EstadoNaoEncontradoException.class)
    public final ResponseEntity<Throwable> handleEstadoNaoEncontradoException(
            final EstadoNaoEncontradoException e) {
        return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(getRootCause(e));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(CidadeNaoEncontradaException.class)
    public final ResponseEntity<Throwable> handleCidadeNaoEncontradaException(
            final CidadeNaoEncontradaException e) {
        return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(getRootCause(e));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<List<Error>>
            handleMethodArgumentNotValidException(
                final MethodArgumentNotValidException e) {
        return ResponseEntity
                    .badRequest()
                    .body(e.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error ->
                            new Error(
                                error.getField(), messageSource.getMessage(
                                    error, LocaleContextHolder.getLocale())
                )).collect(Collectors.toList()));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        /**
         *
         */
        private String field;
        /**
         *
         */
        private String message;
    }
}
