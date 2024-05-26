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
            return ResponseEntity.ok(restauranteFormasPagamentoService.buscaFormasPagamento(restauranteId));
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<?> adicionaFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
            restauranteFormasPagamentoService.adicionaFormaPagamento(restauranteId, formaPagamentoId);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> removeFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
            restauranteFormasPagamentoService.removeFormaPagamento(restauranteId, formaPagamentoId);
            return ResponseEntity.noContent().build();
    }
}
