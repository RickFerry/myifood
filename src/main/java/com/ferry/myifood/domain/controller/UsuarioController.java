package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.UsuarioIN;
import com.ferry.myifood.domain.model.dto.output.GrupoOUT;
import com.ferry.myifood.domain.model.dto.output.UsuarioOUT;
import com.ferry.myifood.domain.model.dto.update.UsuarioUP;
import com.ferry.myifood.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Set;

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
            UsuarioOUT usuario = usuarioService.save(in);
            return ResponseEntity.created(
                    uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri()).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioOUT> update(@PathVariable Long id, @RequestBody @Valid UsuarioUP up) {
            return ResponseEntity.ok(usuarioService.update(id, up));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/{usuarioId}/grupos")
    public ResponseEntity<Set<GrupoOUT>> buscaGrupos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioService.buscaGrupos(usuarioId));
    }

    @PutMapping("/{usuarioId}/grupos/{grupoId}")
    public ResponseEntity<Void> adicionaGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.adicionaGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}/grupos/{grupoId}")
    public ResponseEntity<Void> removeGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.removeGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}
