package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.FotoProdutoIN;
import com.ferry.myifood.domain.model.dto.output.ProdutoOUT;
import com.ferry.myifood.domain.service.RestauranteProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
    /**
     *
     */
    private final RestauranteProdutoService restauranteService;

    @GetMapping
    public ResponseEntity<Set<ProdutoOUT>> buscaProdutos(@PathVariable Long restauranteId) {
            return ResponseEntity.ok(restauranteService.buscaProdutos(restauranteId));
    }

    @GetMapping("/ativos")
    public ResponseEntity<Set<ProdutoOUT>> buscaProdutosAtivos(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(restauranteService.buscaProdutosAtivos(restauranteId));
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoOUT> buscaProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
            return ResponseEntity.ok(restauranteService.buscaProduto(restauranteId, produtoId));
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<?> adicionaProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
            restauranteService.adicionaProduto(restauranteId, produtoId);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<?> removeProduto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
            restauranteService.removeProduto(restauranteId, produtoId);
            return ResponseEntity.noContent().build();
    }

@PutMapping(path = "/{produtoId}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> atualizaFotoProduto(
            @PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoIN fotoProdutoIN) {
        try {
            restauranteService.atualizaFotoProduto(restauranteId, produtoId, fotoProdutoIN);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
