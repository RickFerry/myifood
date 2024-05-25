package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.UsuarioIN;
import com.ferry.myifood.domain.model.dto.output.UsuarioOUT;
import com.ferry.myifood.domain.model.dto.update.UsuarioUP;
import com.ferry.myifood.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    /**
     *
     */
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioOUT>> findAll(Pageable page) {
        return ResponseEntity.ok(usuarioService.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOUT> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioOUT> save(@RequestBody @Valid UsuarioIN in, UriComponentsBuilder uriBuilder) {
        try {
            UsuarioOUT usuario = usuarioService.save(in);
            return ResponseEntity.created(
                    uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri()).body(usuario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioOUT> update(@PathVariable Long id, @RequestBody @Valid UsuarioUP up) {
        try {
            return ResponseEntity.ok(usuarioService.update(id, up));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

