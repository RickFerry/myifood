package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public Page<Estado> listar(Pageable page) {
        return estadoRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Estado buscar(Long id) {
        return estadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        return estadoRepository.findById(id)
                .map(e -> {
                    BeanUtils.copyProperties(estado, e, "id");
                    return estadoRepository.save(e);
                })
                .orElseThrow(() -> new RuntimeException("Não existe um estado com o id informado"));
    }

    @Transactional
    public void deletar(Long id) {
        estadoRepository.findById(id)
                .ifPresentOrElse(estadoRepository::delete,
                        () -> {
                            throw new EntityNotFoundException("Não existe um estado com o id informado");
                        });
    }
}
