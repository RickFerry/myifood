package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.input.EstadoIN;
import com.ferry.myifood.domain.model.dtos.output.EstadoOUT;
import com.ferry.myifood.domain.model.dtos.update.EstadoUP;
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
    public ResponseEntity<Page<EstadoOUT>> listar(Pageable page) {
        return ResponseEntity.ok(estadoService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoOUT> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estadoService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadoOUT> salvar(@RequestBody EstadoIN in, UriComponentsBuilder uriComponentsBuilder) {
        EstadoOUT novo = estadoService.salvar(in);
        URI uri = uriComponentsBuilder.path("/estados/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoOUT> atualizar(@PathVariable Long id, @RequestBody EstadoUP up) {
        try {
            return ResponseEntity.ok(estadoService.atualizar(id, up));
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
