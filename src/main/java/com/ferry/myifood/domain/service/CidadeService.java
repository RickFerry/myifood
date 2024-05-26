package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.CidadeNaoEncontradaException;
import com.ferry.myifood.domain.exception.EstadoNaoEncontradoException;
import com.ferry.myifood.domain.mapper.cidade.CidadeINMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeOUTMapper;
import com.ferry.myifood.domain.mapper.cidade.CidadeUPMapper;
import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.dto.input.CidadeIN;
import com.ferry.myifood.domain.model.dto.output.CidadeOUT;
import com.ferry.myifood.domain.model.dto.update.CidadeUP;
import com.ferry.myifood.domain.repository.CidadeRepository;
import com.ferry.myifood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ferry.myifood.domain.utils.ConstantsUtil.CIDADE_COM_ID_INFORMADO_NAO_EXISTE;
import static com.ferry.myifood.domain.utils.ConstantsUtil.ESTADO_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class CidadeService {
    /**
     *
     */
    private final CidadeRepository cidadeRepository;
    /**
     *
     */
    private final EstadoRepository estadoRepository;
    /**
     *
     */
    private final CidadeOUTMapper cidadeOUTMapper;
    /**
     *
     */
    private final CidadeINMapper cidadeINMapper;
    /**
     *
     */
    private final CidadeUPMapper cidadeUPMapper;

    /**
     * @param page
     * @return Page<CidadeOUT>
     */
    @Transactional(readOnly = true)
    public Page<CidadeOUT> listar(final Pageable page) {
        return cidadeRepository.findAll(page).map(cidadeOUTMapper::toDto);
    }

    /**
     * @param id
     * @return CidadeOUT
     */
    @Transactional(readOnly = true)
    public CidadeOUT buscar(final Long id) {
        return cidadeRepository.findById(id).map(cidadeOUTMapper::toDto).orElseThrow(
                () -> new CidadeNaoEncontradaException(id, CIDADE_COM_ID_INFORMADO_NAO_EXISTE));
    }

    /**
     * @param in
     * @return CidadeOUT
     */
    @Transactional
    public CidadeOUT salvar(final CidadeIN in) {
        Cidade cidade = cidadeINMapper.toEntity(in);
        cidade.setEstado(estadoRepository.findById(in.getEstado().getId()).orElseThrow(
                () -> new EstadoNaoEncontradoException(in.getEstado().getId(), ESTADO_COM_ID_INFORMADO_NAO_EXISTE)));
        return cidadeOUTMapper.toDto(cidadeRepository.save(cidade));
    }

    /**
     * @param id
     * @param up
     * @return CidadeOUT
     */
    @Transactional
    public CidadeOUT atualizar(final Long id, final CidadeUP up) {
        return cidadeOUTMapper.toDto(cidadeUPMapper.partialUpdate(up, cidadeRepository.findById(id).orElseThrow(
                () -> new CidadeNaoEncontradaException(id, CIDADE_COM_ID_INFORMADO_NAO_EXISTE))));
    }

    /**
     * @param id
     */
    @Transactional
    public void remover(final Long id) {
        cidadeRepository.findById(id).ifPresentOrElse(cidadeRepository::delete, () -> {
            throw new CidadeNaoEncontradaException(id, CIDADE_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }
}
