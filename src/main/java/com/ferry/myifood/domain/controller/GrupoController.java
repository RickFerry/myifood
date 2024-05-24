package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.GrupoIN;
import com.ferry.myifood.domain.model.dto.output.GrupoOUT;
import com.ferry.myifood.domain.model.dto.update.GrupoUP;
import com.ferry.myifood.domain.service.GruposService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/grupos")
public class GrupoController {
    /**
     *
     */
    private final GruposService gruposService;

    @GetMapping
    public final ResponseEntity<Page<GrupoOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(gruposService.listar(page));
    }

    @GetMapping("/{id}")
    public final ResponseEntity<GrupoOUT> buscar(@PathVariable final Long id) {
        return ResponseEntity.ok(gruposService.buscar(id));
    }

    @PostMapping
    public final ResponseEntity<?> salvar(@RequestBody @Valid final GrupoIN in, final UriComponentsBuilder uriComponentsBuilder) {
        try {
            GrupoOUT novo = gruposService.salvar(in);
            var uri = uriComponentsBuilder.path("/grupos/{id}").buildAndExpand(novo.getId()).toUri();
            return ResponseEntity.created(uri).body(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public final ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody @Valid final GrupoUP in) {
        try {
            GrupoOUT novo = gruposService.atualizar(id, in);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public final ResponseEntity<?> deletar(@PathVariable final Long id) {
        try {
            gruposService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
