package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.EstadoIN;
import com.ferry.myifood.domain.model.dto.output.EstadoOUT;
import com.ferry.myifood.domain.model.dto.update.EstadoUP;
import com.ferry.myifood.domain.service.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {
    /**
     *
     */
    private final EstadoService estadoService;

    /**
     * @param page
     * @return ResponseEntity<Page < EstadoOUT>>
     */
    @GetMapping
    public final ResponseEntity<Page<EstadoOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(estadoService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<EstadoOUT>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<EstadoOUT> buscar(@PathVariable final Long id) {
        return ResponseEntity.ok(estadoService.buscar(id));
    }

    /**
     * @param in
     * @param uriComponentsBuilder
     * @return ResponseEntity<EstadoOUT>
     */
    @PostMapping
    public final ResponseEntity<EstadoOUT> salvar(@RequestBody final EstadoIN in, final UriComponentsBuilder uriComponentsBuilder) {
        EstadoOUT novo = estadoService.salvar(in);
        var uri = uriComponentsBuilder.path("/estados/{id}").buildAndExpand(novo.getId()).toUri();
        return ResponseEntity.created(uri).body(novo);
    }

    /**
     * @param id
     * @param up
     * @return ResponseEntity<EstadoOUT>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<EstadoOUT> atualizar(@PathVariable final Long id, @RequestBody final EstadoUP up) {
            return ResponseEntity.ok(estadoService.atualizar(id, up));
    }

    /**
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> deletar(@PathVariable final Long id) {
            estadoService.deletar(id);
            return ResponseEntity.noContent().build();
    }
}
