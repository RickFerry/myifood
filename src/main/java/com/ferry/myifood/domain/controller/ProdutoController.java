package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {
    /**
     *
     */
    private final ProdutoService produtoService;

    /**
     * @return ResponseEntity<List<Produto>>
     */
    @GetMapping
    public final ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    /**
     * @param id
     * @return ResponseEntity<Produto>
     */
    @GetMapping("/{id}")
    public final ResponseEntity<Produto> buscar(@PathVariable final Long id) {
            return ResponseEntity.ok(produtoService.buscar(id));
    }
}
