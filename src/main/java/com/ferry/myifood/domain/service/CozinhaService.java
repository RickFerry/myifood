package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.cozinha.CozinhaOUTMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaINMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaUPMapper;
import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import com.ferry.myifood.domain.model.dtos.input.CozinhaIN;
import com.ferry.myifood.domain.model.dtos.update.CozinhaUP;
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
    private final CozinhaOUTMapper cozinhaOUTMapper;
    private final CozinhaINMapper cozinhaINMapper;
    private final CozinhaUPMapper cozinhaUPMapper;

    @Transactional(readOnly = true)
    public Page<CozinhaOUT> listar(Pageable page) {
        return cozinhaRepository.findAll(page).map(cozinhaOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public CozinhaOUT pegar(Long id) {
        return cozinhaRepository.findById(id).map(cozinhaOUTMapper::toDto)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND));
    }

    @Transactional
    public CozinhaOUT salvar(CozinhaIN in) {
        return cozinhaOUTMapper.toDto(cozinhaRepository.save(cozinhaINMapper.toEntity(in)));
    }

    @Transactional
    public CozinhaOUT atualizar(Long id, CozinhaUP up) {
        return cozinhaOUTMapper.toDto(cozinhaUPMapper.partialUpdate(up, cozinhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND))));
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
