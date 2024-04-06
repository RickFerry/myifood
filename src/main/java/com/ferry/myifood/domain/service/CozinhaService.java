package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;
    private static final String NOT_FOUND = "Cozinha não encontrada";

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha pegar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
    }

    @Transactional
    public Cozinha criar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        return cozinhaRepository.findById(id)
                .map(c -> {
                    BeanUtils.copyProperties(cozinha, c, "id");
                    return cozinhaRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
    }

    @Transactional
    public void remover(Long id) {
        cozinhaRepository.findById(id)
                .ifPresentOrElse(cozinhaRepository::delete,
                        () -> {
                            throw new EntityNotFoundException(NOT_FOUND);
                        });
    }
}
