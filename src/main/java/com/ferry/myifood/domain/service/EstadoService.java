package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.estado.EstadoINMapper;
import com.ferry.myifood.domain.mapper.estado.EstadoOUTMapper;
import com.ferry.myifood.domain.mapper.estado.EstadoUPMapper;
import com.ferry.myifood.domain.model.Estado;
import com.ferry.myifood.domain.model.dtos.input.EstadoIN;
import com.ferry.myifood.domain.model.dtos.output.EstadoOUT;
import com.ferry.myifood.domain.model.dtos.update.EstadoUP;
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
    private final EstadoOUTMapper estadoOUTMapper;
    private final EstadoINMapper estadoINMapper;
    private final EstadoUPMapper estadoUPMapper;

    @Transactional(readOnly = true)
    public Page<EstadoOUT> listar(Pageable page) {
        return estadoRepository.findAll(page).map(estadoOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public EstadoOUT buscar(Long id) {
        return estadoRepository.findById(id).map(estadoOUTMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
    }

    @Transactional
    public EstadoOUT salvar(EstadoIN in) {
        return estadoOUTMapper.toDto(estadoRepository.save(estadoINMapper.toEntity(in)));
    }

    @Transactional
    public EstadoOUT atualizar(Long id, EstadoUP up) {
        return estadoOUTMapper.toDto(estadoUPMapper.partialUpdate(up, estadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não existe um estado com o id informado"))));
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
