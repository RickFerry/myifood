package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.CozinhaIN;
import com.ferry.myifood.domain.model.dto.output.CozinhaOUT;
import com.ferry.myifood.domain.model.dto.update.CozinhaUP;
import com.ferry.myifood.domain.service.CozinhaService;
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
@RequestMapping("/cozinhas")
public class CozinhaController {
    /**
     *
     */
    private final CozinhaService cozinhaService;

    /**
     * @param page
     * @return ResponseEntity<Page < CozinhaOUT>>
     */
    @GetMapping
    public final ResponseEntity<Page<CozinhaOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(cozinhaService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<CozinhaOUT>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<CozinhaOUT> pegar(@PathVariable final Long id) {
            return ResponseEntity.ok(cozinhaService.pegar(id));
    }

    /**
     * @param cozinhaIN
     * @param uriComponentsBuilder
     * @return ResponseEntity<CozinhaOUT>
     */
    @PostMapping
    public final ResponseEntity<CozinhaOUT> salvar(@RequestBody @Valid final CozinhaIN cozinhaIN, final UriComponentsBuilder uriComponentsBuilder) {
        CozinhaOUT nova = cozinhaService.salvar(cozinhaIN);
        var uri = uriComponentsBuilder.path("/cozinhas/{id}").buildAndExpand(nova.getId()).toUri();
        return ResponseEntity.created(uri).body(nova);
    }

    /**
     * @param id
     * @param up
     * @return ResponseEntity<CozinhaOUT>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<CozinhaOUT> atualizar(@PathVariable final Long id, @RequestBody @Valid final CozinhaUP up) {
        return ResponseEntity.ok(cozinhaService.atualizar(id, up));
    }

    /**
     * @param id
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> remover(@PathVariable final Long id) {
        cozinhaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
