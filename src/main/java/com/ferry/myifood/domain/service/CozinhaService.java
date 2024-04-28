package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.cozinha.CozinhaOUTMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaINMapper;
import com.ferry.myifood.domain.model.Cozinha;
import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import com.ferry.myifood.domain.model.dtos.input.CozinhaIN;
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
    private static final String NOT_FOUND = "Cozinha não encontrada";
    private final CozinhaRepository cozinhaRepository;
    private final CozinhaOUTMapper cozinhaMapper;
    private final CozinhaINMapper cozinhaINMapper;

    @Transactional(readOnly = true)
    public Page<CozinhaOUT> listar(Pageable page) {
        return cozinhaRepository.findAll(page).map(cozinhaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public CozinhaOUT pegar(Long id) {
        return cozinhaRepository.findById(id).map(cozinhaMapper::toDto)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
    }

    @Transactional
    public CozinhaOUT salvar(CozinhaIN in) {
        return cozinhaMapper.toDto(cozinhaRepository.save(cozinhaINMapper.toEntity(in)));
    }

    @Transactional
    public CozinhaOUT atualizar(Long id, Cozinha cozinha) {
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
