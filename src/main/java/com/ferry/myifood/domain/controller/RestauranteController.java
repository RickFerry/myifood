package com.ferry.myifood.domain.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ferry.myifood.domain.model.Restaurante;
import com.ferry.myifood.domain.model.dtos.RestauranteDto;
import com.ferry.myifood.domain.service.RestauranteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurantes")
public class RestauranteController {
    /**
     *
     */
    private final RestauranteService restauranteService;

    /**
     * @param page
     * @return ResponseEntity<Page<RestauranteDto>>
     */
    @GetMapping
    public final ResponseEntity<Page<RestauranteDto>> listar(
            final Pageable page) {
        return ResponseEntity.ok(restauranteService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<RestauranteDto>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<RestauranteDto> buscar(
            @PathVariable final Long id) {
        try {
            return ResponseEntity.ok(restauranteService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param restaurante
     * @param uriComponentsBuilder
     * @return ResponseEntity<?>
     */
    @PostMapping
    public final ResponseEntity<?> salvar(
            @RequestBody @Valid final Restaurante restaurante,
                final UriComponentsBuilder uriComponentsBuilder) {
        try {
            RestauranteDto novo = restauranteService.salvar(restaurante);
            var uri = uriComponentsBuilder
            .path("/restaurantes/{id}")
            .buildAndExpand(novo.getId())
            .toUri();
            return ResponseEntity.created(uri).body(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @param id
     * @param dto
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<?> atualizar(@PathVariable final Long id,
            @RequestBody final RestauranteDto dto) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(id, dto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<?> deletar(@PathVariable final Long id) {
        try {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
