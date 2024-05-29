package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.dto.input.RestauranteIN;
import com.ferry.myifood.domain.model.dto.output.RestauranteOUT;
import com.ferry.myifood.domain.model.dto.output.UsuarioDetalhe;
import com.ferry.myifood.domain.model.dto.update.RestauranteUP;
import com.ferry.myifood.domain.service.RestauranteService;
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
@RequestMapping("/restaurantes")
public class RestauranteController {
    /**
     *
     */
    private final RestauranteService restauranteService;

    /**
     * @param page
     * @return ResponseEntity<Page < RestauranteDto>>
     */
    @GetMapping
    public final ResponseEntity<Page<RestauranteOUT>> listar(final Pageable page) {
        return ResponseEntity.ok(restauranteService.listar(page));
    }

    /**
     * @param id
     * @return ResponseEntity<RestauranteDto>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<RestauranteOUT> buscar(@PathVariable final Long id) {
            return ResponseEntity.ok(restauranteService.buscar(id));
    }

    /**
     * @param in
     * @param uriComponentsBuilder
     * @return ResponseEntity<?>
     */
    @PostMapping
    public final ResponseEntity<?> salvar(@RequestBody @Valid final RestauranteIN in, final UriComponentsBuilder uriComponentsBuilder) {
            RestauranteOUT novo = restauranteService.salvar(in);
            var uri = uriComponentsBuilder.path("/restaurantes/{id}").buildAndExpand(novo.getId()).toUri();
            return ResponseEntity.created(uri).body(novo);
    }

    /**
     * @param id
     * @param up
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}")
    public final ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody @Valid final RestauranteUP up) {
            return ResponseEntity.ok(restauranteService.atualizar(id, up));
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<?> deletar(@PathVariable final Long id) {
            restauranteService.deletar(id);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}/ativar")
    public final ResponseEntity<?> ativar(@PathVariable final Long id) {
            restauranteService.ativar(id);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{id}/inativar")
    public final ResponseEntity<?> inativar(@PathVariable final Long id) {
        restauranteService.inativar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param ids
     * @return ResponseEntity<?>
     */
    @PutMapping("/ativacoes")
    public final ResponseEntity<?> ativacoes(@RequestBody final Set<Long> ids) {
            restauranteService.ativacoes(ids);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param ids
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/inativacoes")
    public final ResponseEntity<?> inativacoes(@RequestBody final Set<Long> ids) {
            restauranteService.inativacoes(ids);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}/abertura")
    public final ResponseEntity<?> abrir(@PathVariable final Long id) {
            restauranteService.abrir(id);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @return ResponseEntity<?>
     */
    @PutMapping("/{id}/fechamento")
    public final ResponseEntity<?> fechar(@PathVariable final Long id) {
            restauranteService.fechar(id);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param idRestaurante
     * @return ResponseEntity<?>
     */
    @GetMapping("/{idRestaurante}/responsaveis")
    public final ResponseEntity<Set<UsuarioDetalhe>> listarResponsaveis(@PathVariable final Long idRestaurante) {
            return ResponseEntity.ok(restauranteService.listarResponsaveis(idRestaurante));
    }

    /**
     * @param idRestaurante
     * @param idResponsavel
     * @return ResponseEntity<?>
     */
    @PutMapping("/{idRestaurante}/responsaveis/{idResponsavel}")
    public final ResponseEntity<?> adicionarResponsavel(@PathVariable final Long idRestaurante, @PathVariable final Long idResponsavel) {
            restauranteService.adicionarResponsavel(idRestaurante, idResponsavel);
            return ResponseEntity.noContent().build();
    }

    /**
     * @param idRestaurante
     * @param idResponsavel
     * @return ResponseEntity<?>
     */
    @DeleteMapping("/{idRestaurante}/responsaveis/{idResponsavel}")
    public final ResponseEntity<?> removerResponsavel(@PathVariable final Long idRestaurante, @PathVariable final Long idResponsavel) {
            restauranteService.removerResponsavel(idRestaurante, idResponsavel);
            return ResponseEntity.noContent().build();
    }
}
