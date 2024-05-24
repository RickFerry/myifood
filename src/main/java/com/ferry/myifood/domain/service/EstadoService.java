package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.estado.EstadoINMapper;
import com.ferry.myifood.domain.mapper.estado.EstadoOUTMapper;
import com.ferry.myifood.domain.mapper.estado.EstadoUPMapper;
import com.ferry.myifood.domain.model.dto.input.EstadoIN;
import com.ferry.myifood.domain.model.dto.output.EstadoOUT;
import com.ferry.myifood.domain.model.dto.update.EstadoUP;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class EstadoService {
    /**
     *
     */
    private final EstadoRepository estadoRepository;
    /**
     *
     */
    private final EstadoOUTMapper estadoOUTMapper;
    /**
     *
     */
    private final EstadoINMapper estadoINMapper;
    /**
     *
     */
    private final EstadoUPMapper estadoUPMapper;

    /**
     * @param page
     * @return Page<EstadoOUT>
     */
    @Transactional(readOnly = true)
    public Page<EstadoOUT> listar(final Pageable page) {
        return estadoRepository.findAll(page).map(estadoOUTMapper::toDto);
    }

    /**
     * @param id
     * @return EstadoOUT
     */
    @Transactional(readOnly = true)
    public EstadoOUT buscar(final Long id) {
        return estadoRepository.findById(id).map(estadoOUTMapper::toDto)
                .orElseThrow(() ->
                new RuntimeException("Estado não encontrado"));
    }

    /**
     * @param in
     * @return EstadoOUT
     */
    @Transactional
    public EstadoOUT salvar(final EstadoIN in) {
        return estadoOUTMapper
                .toDto(estadoRepository.save(estadoINMapper.toEntity(in)));
    }

    /**
     * @param id
     * @param up
     * @return EstadoOUT
     */
    @Transactional
    public EstadoOUT atualizar(final Long id, final EstadoUP up) {
        return estadoOUTMapper
                .toDto(estadoUPMapper
                .partialUpdate(up, estadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Não existe um estado com o id informado"))));
    }

    /**
     * @param id
     */
    @Transactional
    public void deletar(final Long id) {
        estadoRepository.findById(id)
                .ifPresentOrElse(estadoRepository::delete,
                        () -> {
                            throw new EntityNotFoundException(
                                "Não existe um estado com o id informado");
                        });
    }
}
