package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.CidadeNaoEncontradaException;
import com.ferry.myifood.domain.exception.EstadoNaoEncontradoException;
import com.ferry.myifood.domain.mapper.cidade.CidadeINMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeOUTMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeUPMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dtos.output.CidadeOUT;
import com.ferry.myifood.domain.model.dtos.input.CidadeIN;
import com.ferry.myifood.domain.model.dtos.update.CidadeUP;
import com.ferry.myifood.domain.repository.CidadeRepository;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.ferry.myifood.domain.utils.ConstantsUtil.NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO;
import static com.ferry.myifood.domain.utils.ConstantsUtil.NAO_EXISTE_ESTADO_COM_O_ID_INFORMADO;

@Service
@AllArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeOUTMapper cidadeOUTMapper;
    private final CidadeINMapper cidadeINMapper;
    private final CidadeUPMapper cidadeUPMapper;

    @Transactional(readOnly = true)
    public Page<CidadeOUT> listar(Pageable page) {
        return cidadeRepository.findAll(page).map(cidadeOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public CidadeOUT buscar(Long id) {
        return cidadeRepository.findById(id).map(cidadeOUTMapper::toDto).orElseThrow(
                () -> new CidadeNaoEncontradaException(NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO));
    }

    @Transactional
    public CidadeOUT salvar(CidadeIN in) {
        Cidade cidade = cidadeINMapper.toEntity(in);
        cidade.setEstado(estadoRepository.findById(in.getEstado().getId())
                .orElseThrow(
                        () -> new EstadoNaoEncontradoException(NAO_EXISTE_ESTADO_COM_O_ID_INFORMADO)));
        return cidadeOUTMapper.toDto(cidadeRepository.save(cidade));
    }

    @Transactional
    public CidadeOUT atualizar(Long id, CidadeUP up) {
        return cidadeOUTMapper.toDto(cidadeUPMapper.partialUpdate(up, cidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO))));
    }

    @Transactional
    public void remover(Long id) {
        cidadeRepository.findById(id)
                .ifPresentOrElse(cidadeRepository::delete,
                        () -> {
                            throw new EntityNotFoundException(NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO);
                        });
    }
}
