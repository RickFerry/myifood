package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.FormasPagamentoNaoEncontradaException;
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

import static com.ferry.myifood.domain.utils.ConstantsUtil.FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class FormasPagamentoService {
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
                .orElseThrow(
                        () -> new FormasPagamentoNaoEncontradaException(id, FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE));
    }

    @Transactional
    public FormasPagamentoOUT save(FormasPagamentoIN in) {
        return formaPagamentoOUTMapper.toDto(formaPagamentoRepository.save(formaPagamentoINMapper.toEntity(in)));
    }

    @Transactional
    public FormasPagamentoOUT update(Long id, FormasPagamentoUP up) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id)
                .map(formaPagamentoSalva -> formaPagamentoUPMapper.partialUpdate(up, formaPagamentoSalva))
                .orElseThrow(
                        () -> new FormasPagamentoNaoEncontradaException(id, FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE));
        return formaPagamentoOUTMapper.toDto(formaPagamentoRepository.save(formaPagamento));
    }

    @Transactional
    public void delete(Long id) {
        formaPagamentoRepository.findById(id)
                .ifPresentOrElse(formaPagamentoRepository::delete, () -> {
                    throw new FormasPagamentoNaoEncontradaException(id, FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE);
                });
    }
}
