package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<Page<Cozinha>> listar(Pageable page) {
    return ResponseEntity.ok(cozinhaService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> pegar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cozinhaService.pegar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha, UriComponentsBuilder uriComponentsBuilder) {
        Cozinha nova = cozinhaService.salvar(cozinha);
        URI uri = uriComponentsBuilder.path("/cozinhas/{id}").buildAndExpand(nova.getId()).toUri();
        return ResponseEntity.created(uri).body(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        try {
            return ResponseEntity.ok(cozinhaService.atualizar(id, cozinha));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            cozinhaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
