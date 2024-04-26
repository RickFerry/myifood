package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dtos.CidadeDto;
import com.ferry.myifood.domain.service.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<Page<CidadeDto>> listar(Pageable page) {
        return ResponseEntity.ok(cidadeService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cidadeService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Cidade cidade, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Cidade nova = cidadeService.salvar(cidade);
            URI uri = uriComponentsBuilder.path("/cidades/{id}").buildAndExpand(nova.getId()).toUri();
            return ResponseEntity.created(uri).body(nova);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            return ResponseEntity.ok(cidadeService.atualizar(id, cidade));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            cidadeService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
