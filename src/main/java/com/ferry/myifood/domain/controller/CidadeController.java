package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dtos.input.CidadeIN;
import com.ferry.myifood.domain.model.dtos.output.CidadeOUT;
import com.ferry.myifood.domain.model.dtos.update.CidadeUP;
import com.ferry.myifood.domain.service.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {
    /**
     *
     */
    private final CidadeService cidadeService;

    /**
     * @param page
     * @return ResponseEntity<Page<CidadeOUT>>
     */
    @GetMapping
    public final ResponseEntity<Page<CidadeOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(cidadeService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<CidadeOUT>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<CidadeOUT> buscar(@PathVariable final Long id) {
        try {
            return ResponseEntity.ok(cidadeService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param in
     * @param uriComponentsBuilder
     * @return ResponseEntity<CidadeOUT>
     */
    @PostMapping
    public final ResponseEntity<?> salvar(@RequestBody @Valid final CidadeIN in,
            final UriComponentsBuilder uriComponentsBuilder) {
        try {
            CidadeOUT nova = cidadeService.salvar(in);
            var uri = uriComponentsBuilder
                .path("/cidades/{id}")
                .buildAndExpand(nova.getId())
                .toUri();
            return ResponseEntity.created(uri).body(nova);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @param id
     * @param up
     * @return ResponseEntity<CidadeOUT>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<?> atualizar(
            @PathVariable final Long id, @RequestBody final CidadeUP up) {
        try {
            return ResponseEntity.ok(cidadeService.atualizar(id, up));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> remover(@PathVariable final Long id) {
        try {
            cidadeService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
