package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaINMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaOUTMapper;
import com.ferry.myifood.domain.mapper.cozinha.CozinhaUPMapper;
import com.ferry.myifood.domain.model.dto.input.CozinhaIN;
import com.ferry.myifood.domain.model.dto.output.CozinhaOUT;
import com.ferry.myifood.domain.model.dto.update.CozinhaUP;
import com.ferry.myifood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ferry.myifood.domain.utils.ConstantsUtil.COZINHA_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class CozinhaService {
    /**
     *
     */
    private final CozinhaRepository cozinhaRepository;
    /**
     *
     */
    private final CozinhaOUTMapper cozinhaOUTMapper;
    /**
     *
     */
    private final CozinhaINMapper cozinhaINMapper;
    /**
     *
     */
    private final CozinhaUPMapper cozinhaUPMapper;

    /**
     * @param page
     * @return Page<CozinhaOUT>
     */
    @Transactional(readOnly = true)
    public Page<CozinhaOUT> listar(final Pageable page) {
        return cozinhaRepository.findAll(page).map(cozinhaOUTMapper::toDto);
    }

    /**
     * @param id
     * @return CozinhaOUT
     */
    @Transactional(readOnly = true)
    public CozinhaOUT pegar(final Long id) {
        return cozinhaRepository.findById(id).map(cozinhaOUTMapper::toDto).orElseThrow(
                () -> new CozinhaNaoEncontradaException(id, COZINHA_COM_ID_INFORMADO_NAO_EXISTE));
    }

    /**
     * @param in
     * @return CozinhaOUT
     */
    @Transactional
    public CozinhaOUT salvar(final CozinhaIN in) {
        return cozinhaOUTMapper.toDto(cozinhaRepository.save(cozinhaINMapper.toEntity(in)));
    }

    /**
     * @param id
     * @param up
     * @return CozinhaOUT
     */
    @Transactional
    public CozinhaOUT atualizar(final Long id, final CozinhaUP up) {
        return cozinhaOUTMapper.toDto(cozinhaUPMapper.partialUpdate(up, cozinhaRepository.findById(id).orElseThrow(
                () -> new CozinhaNaoEncontradaException(id, COZINHA_COM_ID_INFORMADO_NAO_EXISTE))));
    }

    /**
     * @param id
     */
    @Transactional
    public void remover(final Long id) {
        cozinhaRepository.findById(id).ifPresentOrElse(cozinhaRepository::delete, () -> {
            throw new CozinhaNaoEncontradaException(id, COZINHA_COM_ID_INFORMADO_NAO_EXISTE);
        });
    }
}
