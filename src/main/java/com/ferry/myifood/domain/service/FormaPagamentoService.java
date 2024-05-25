package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.formapagamento.FormasPagamentoINMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormasPagamentoOUTMapper;
import com.ferry.myifood.domain.mapper.formapagamento.FormasPagamentoUPMapper;
import com.ferry.myifood.domain.model.FormaPagamento;
import com.ferry.myifood.domain.model.dto.input.FormasPagamentoIN;
import com.ferry.myifood.domain.model.dto.output.FormasPagamentoOUT;
import com.ferry.myifood.domain.model.dto.update.FormasPagamentoUP;
import com.ferry.myifood.domain.repository.FormasPagamentoRepository;
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
    private final FormasPagamentoRepository formaPagamentoRepository;
    /**
     *
     */
    private final FormasPagamentoINMapper formaPagamentoINMapper;
    /**
     *
     */
    private final FormasPagamentoUPMapper formaPagamentoUPMapper;
    /**
     *
     */
    private final FormasPagamentoOUTMapper formaPagamentoOUTMapper;

    @Transactional(readOnly = true)
    public Page<FormasPagamentoOUT> findAll(Pageable pageable) {
        return formaPagamentoRepository.findAll(pageable).map(formaPagamentoOUTMapper::toDto);
    }

    @Transactional(readOnly = true)
    public FormasPagamentoOUT findById(Long id) {
        return formaPagamentoRepository.findById(id)
                .map(formaPagamentoOUTMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Forma de pagamento não encontrada"));
    }

    @Transactional
    public FormasPagamentoOUT save(FormasPagamentoIN in) {
        return formaPagamentoOUTMapper.toDto(formaPagamentoRepository.save(formaPagamentoINMapper.toEntity(in)));
    }

    @Transactional
    public FormasPagamentoOUT update(Long id, FormasPagamentoUP up) {
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
