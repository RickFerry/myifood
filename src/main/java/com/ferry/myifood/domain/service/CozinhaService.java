package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha pegar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cozinha não encontrada"));
    }
}
