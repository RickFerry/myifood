package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.CozinhaMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dtos.CozinhaDto;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaMapper cozinhaMapper;
    private static final String NOT_FOUND = "Cozinha não encontrada";

    @Transactional(readOnly = true)
    public Page<CozinhaDto> listar(Pageable page) {
        return cozinhaRepository.findAll(page).map(cozinhaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public CozinhaDto pegar(Long id) {
        return cozinhaRepository.findById(id).map(cozinhaMapper::toDto)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
    }

    @Transactional
    public CozinhaDto salvar(Cozinha cozinha) {
        return cozinhaMapper.toDto(cozinhaRepository.save(cozinha));
    }

    @Transactional
    public CozinhaDto atualizar(Long id, Cozinha cozinha) {
        return cozinhaRepository.findById(id)
                .map(c -> {
                    BeanUtils.copyProperties(cozinha, c, "id");
                    return cozinhaMapper.toDto(cozinhaRepository.save(c));
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
