package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(restauranteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(
            @RequestBody Restaurante restaurante, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Restaurante novo = restauranteService.salvar(restaurante);
            return ResponseEntity.created(
                    uriComponentsBuilder
                            .path("/restaurantes/{id}")
                            .buildAndExpand(novo.getId())
                            .toUri()
            ).body(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(id, restaurante));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
