package com.ferry.myifood.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(CidadeNaoEncontradaException.class)
    public final ResponseEntity<Error> handleCidadeNaoEncontradaException(final CidadeNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(EstadoNaoEncontradoException.class)
    public final ResponseEntity<Error> handleEstadoNaoEncontradoException(final EstadoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(CozinhaNaoEncontradaException.class)
    public final ResponseEntity<Error> handleCozinhaNaoEncontradaException(final CozinhaNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(RestauranteNaoEncontradoException.class)
    public final ResponseEntity<Error> handleRestauranteNaoEncontradoException(final RestauranteNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(FormasPagamentoNaoEncontradaException.class)
    public final ResponseEntity<Error> handleFormasPagamentoNaoEncontradaException(final FormasPagamentoNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public final ResponseEntity<Error> handleProdutoNaoEncontradoException(final ProdutoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public final ResponseEntity<Error> handleUsuarioNaoEncontradoException(final UsuarioNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(GrupoNaoEncontradoException.class)
    public final ResponseEntity<Error> handleGrupoNaoEncontradoException(final GrupoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    @ExceptionHandler(PermissaoNaoEncontradaException.class)
    public final ResponseEntity<Error> handlePermissaoNaoEncontradaException(final PermissaoNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getId().toString(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(UniqueConstraintViolationException.class)
    public final ResponseEntity<Error> handleUniqueConstraintViolationException(final UniqueConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getFieldName(), e.getMessage()));
    }

    /**
     * @param e
     * @return ResponseEntity<Throwable>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<List<Error>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getBindingResult().getFieldErrors().stream().map(
                error -> new Error(error.getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList()));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        /**
         *
         */
        private String cause;
        /**
         *
         */
        private String message;
    }
}
