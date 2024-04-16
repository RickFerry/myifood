package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.CidadeNaoEncontradaException;
import com.ferry.myifood.domain.exception.EstadoNaoEncontradoException;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.repository.CidadeRepository;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static com.ferry.myifood.domain.utils.ContantsUtil.NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO;
import static com.ferry.myifood.domain.utils.ContantsUtil.NAO_EXISTE_ESTADO_COM_O_ID_INFORMADO;

@Service
@AllArgsConstructor
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public Page<Cidade> listar(Pageable page) {
        return cidadeRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(
                () -> new CidadeNaoEncontradaException(NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        cidade.setEstado(estadoRepository.findById(cidade.getEstado().getId())
                .orElseThrow(
                        () -> new EstadoNaoEncontradoException(NAO_EXISTE_ESTADO_COM_O_ID_INFORMADO)));
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade) {
        return cidadeRepository.findById(id)
                .map(c -> {
                    c.setEstado(estadoRepository.findById(cidade.getEstado().getId())
                            .orElseThrow(
                                    () -> new EstadoNaoEncontradoException(NAO_EXISTE_ESTADO_COM_O_ID_INFORMADO)));
                    BeanUtils.copyProperties(cidade, c, "id");
                    return cidadeRepository.save(c);
                })
                .orElseThrow(() -> new EntityNotFoundException(NAO_EXISTE_CIDADE_COM_O_ID_INFORMADO));
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
