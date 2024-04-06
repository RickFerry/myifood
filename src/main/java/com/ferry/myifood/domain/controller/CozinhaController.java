package com.ferry.myifood.domain.controller;

import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.service.CozinhaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(cozinhaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> pegar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cozinhaService.pegar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
