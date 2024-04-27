package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.EstadoDto;
import com.ferry.myifood.domain.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<Page<EstadoDto>> listar(Pageable page) {
        return ResponseEntity.ok(estadoService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estadoService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadoDto> salvar(@RequestBody Estado estado, UriComponentsBuilder uriComponentsBuilder) {
        EstadoDto novo = estadoService.salvar(estado);
        URI uri = uriComponentsBuilder.path("/estados/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        try {
            return ResponseEntity.ok(estadoService.atualizar(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            estadoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
