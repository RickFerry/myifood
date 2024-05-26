package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Permissao;
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
import java.util.Set;

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
            GrupoOUT novo = gruposService.salvar(in);
            var uri = uriComponentsBuilder.path("/grupos/{id}").buildAndExpand(novo.getId()).toUri();
            return ResponseEntity.created(uri).body(novo);
    }

    @PutMapping("/{id}")
    public final ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody @Valid final GrupoUP in) {
            GrupoOUT novo = gruposService.atualizar(id, in);
            return ResponseEntity.ok(novo);
    }

    @DeleteMapping("/{id}")
    public final ResponseEntity<?> deletar(@PathVariable final Long id) {
            gruposService.deletar(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/permissoes/{permissaoId}")
    public final ResponseEntity<?> adicionarPermissao(@PathVariable final Long id, @PathVariable final Long permissaoId) {
            gruposService.adicionarPermissao(id, permissaoId);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/permissoes/{permissaoId}")
    public final ResponseEntity<?> deletarPermissao(@PathVariable final Long id, @PathVariable final Long permissaoId) {
            gruposService.deletarPermissao(id, permissaoId);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/permissoes")
    public final ResponseEntity<Set<Permissao>> listarPermissoes(@PathVariable final Long id) {
            return ResponseEntity.ok(gruposService.listarPermissoes(id));
    }
}
