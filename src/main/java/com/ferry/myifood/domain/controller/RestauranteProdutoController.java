package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.RestauranteIN;
import com.ferry.myifood.domain.model.dto.output.ProdutoOUT;
import com.ferry.myifood.domain.model.dto.output.RestauranteOUT;
import com.ferry.myifood.domain.model.dto.update.RestauranteUP;
import com.ferry.myifood.domain.service.RestauranteProdutoService;
import com.ferry.myifood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
}