package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;
import com.ferry.myifood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<Page<RestauranteDto>> listar(Pageable page) {
        return ResponseEntity.ok(restauranteService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDto> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(
            @RequestBody @Valid Restaurante restaurante, UriComponentsBuilder uriComponentsBuilder) {
        try {
            RestauranteDto novo = restauranteService.salvar(restaurante);
            URI uri = uriComponentsBuilder.path("/restaurantes/{id}").buildAndExpand(novo.getId()).toUri();
            return ResponseEntity.created(uri).body(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody RestauranteDto dto) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
