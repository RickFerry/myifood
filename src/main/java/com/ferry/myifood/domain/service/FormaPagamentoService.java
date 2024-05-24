package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.formapagamento.FormaPagamentoINMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormaPagamentoOUTMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormaPagamentoUPMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.input.FormaPagamentoIN;
import com.ferry.myifood.domain.model.dto.output.FormaPagamentoOUT;
import com.ferry.myifood.domain.model.dto.update.FormaPagamentoUP;
import com.ferry.myifood.domain.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class FormaPagamentoService {
    /**
     *
     */
    private final FormaPagamentoRepository formaPagamentoRepository;
    /**
     *
     */
    private final FormaPagamentoINMapper formaPagamentoINMapper;
    /**
     *
     */
    private final FormaPagamentoUPMapper formaPagamentoUPMapper;
    /**
     *
     */
    private final FormaPagamentoOUTMapper formaPagamentoOUTMapper;

    @Transactional(readOnly = true)
    public Page<FormaPagamentoOUT> findAll(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable).map(formaPagamentoOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public FormaPagamentoOUT findById(Long id) {
        return formaPagamentoRepository.findById(id)
                .map(formaPagamentoOUTMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Forma de pagamento não encontrada"));
    }

    @Transactional
    public FormaPagamentoOUT save(FormaPagamentoIN in) {
        return formaPagamentoOUTMapper.toDto(formaPagamentoRepository.save(formaPagamentoINMapper.toEntity(in)));
    }

    @Transactional
    public FormaPagamentoOUT update(Long id, FormaPagamentoUP up) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id)
                .map(formaPagamentoSalva -> formaPagamentoUPMapper.partialUpdate(up, formaPagamentoSalva))
                .orElseThrow(() -> new EntityNotFoundException("Forma de pagamento não encontrada"));
        return formaPagamentoOUTMapper.toDto(formaPagamentoRepository.save(formaPagamento));
    }

    @Transactional
    public void delete(Long id) {
        formaPagamentoRepository.findById(id)
                .ifPresentOrElse(formaPagamentoRepository::delete, () -> {
                    throw new EntityNotFoundException("Forma de pagamento não encontrada");
                });
    }
}
