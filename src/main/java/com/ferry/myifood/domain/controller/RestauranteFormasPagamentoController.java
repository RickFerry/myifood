package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.output.FormasPagamentoOUT;
import com.ferry.myifood.domain.service.RestauranteFormasPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController {
    /**
     *
     */
    private final RestauranteFormasPagamentoService restauranteFormasPagamentoService;

    @GetMapping
    public ResponseEntity<Set<FormasPagamentoOUT>> buscaFormasPagamento(@PathVariable Long restauranteId) {
        try {
            return ResponseEntity.ok(restauranteFormasPagamentoService.buscaFormasPagamento(restauranteId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<?> adicionaFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        try {
            restauranteFormasPagamentoService.adicionaFormaPagamento(restauranteId, formaPagamentoId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> removeFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        try {
            restauranteFormasPagamentoService.removeFormaPagamento(restauranteId, formaPagamentoId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
