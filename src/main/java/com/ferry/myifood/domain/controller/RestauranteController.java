package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.RestauranteIN;
import com.ferry.myifood.domain.model.dto.output.RestauranteOUT;
import com.ferry.myifood.domain.model.dto.update.RestauranteUP;
import com.ferry.myifood.domain.service.RestauranteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

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
     * @return ResponseEntity<Page < RestauranteDto>>
     */
    @GetMapping
    public final ResponseEntity<Page<RestauranteOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(restauranteService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<RestauranteDto>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<RestauranteOUT> buscar(@PathVariable final Long id) {
        try {
            return ResponseEntity.ok(restauranteService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param in
     * @param uriComponentsBuilder
     * @return ResponseEntity<?>
     */
    @PostMapping
    public final ResponseEntity<?> salvar(@RequestBody @Valid final RestauranteIN in, final UriComponentsBuilder uriComponentsBuilder) {
        try {
            RestauranteOUT novo = restauranteService.salvar(in);
            var uri = uriComponentsBuilder.path("/restaurantes/{id}").buildAndExpand(novo.getId()).toUri();
            return ResponseEntity.created(uri).body(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @param id
     * @param up
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody @Valid final RestauranteUP up) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(id, up));
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

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}/ativar")
    public final ResponseEntity<?> ativar(@PathVariable final Long id) {
        try {
            restauranteService.ativar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{id}/inativar")
    public final ResponseEntity<?> inativar(@PathVariable final Long id) {
        try {
            restauranteService.inativar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
