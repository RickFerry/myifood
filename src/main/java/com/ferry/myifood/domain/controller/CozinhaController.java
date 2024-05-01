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

import com.ferry.myifood.domain.model.dtos.input.CozinhaIN;
import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import com.ferry.myifood.domain.model.dtos.update.CozinhaUP;
import com.ferry.myifood.domain.service.CozinhaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    /**
     *
     */
    private final CozinhaService cozinhaService;

    /**
     * @param page
     * @return ResponseEntity<Page<CozinhaOUT>>
     */
    @GetMapping
    public final ResponseEntity<Page<CozinhaOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(cozinhaService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<CozinhaOUT>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<CozinhaOUT> pegar(@PathVariable final Long id) {
        try {
            return ResponseEntity.ok(cozinhaService.pegar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param cozinhaIN
     * @param uriComponentsBuilder
     * @return ResponseEntity<CozinhaOUT>
     */
    @PostMapping
    public final ResponseEntity<CozinhaOUT> salvar(
        @RequestBody @Valid final CozinhaIN cozinhaIN,
        final UriComponentsBuilder uriComponentsBuilder) {
        CozinhaOUT nova = cozinhaService.salvar(cozinhaIN);
        var uri = uriComponentsBuilder
            .path("/cozinhas/{id}")
            .buildAndExpand(nova.getId())
            .toUri();
        return ResponseEntity.created(uri).body(nova);
    }

    /**
     * @param id
     * @param up
     * @return ResponseEntity<CozinhaOUT>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<CozinhaOUT> atualizar(
        @PathVariable final Long id,
            @RequestBody @Valid final CozinhaUP up) {
        try {
            return ResponseEntity.ok(cozinhaService.atualizar(id, up));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> remover(@PathVariable final Long id) {
        try {
            cozinhaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
