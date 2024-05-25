package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.formapagamento.FormasPagamentoOUTMapper;
import com.ferry.myifood.domain.model.dto.output.FormasPagamentoOUT;
import com.ferry.myifood.domain.repository.FormasPagamentoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@AllArgsConstructor
public class RestauranteFormasPagamentoService {
    /**
     *
     */
    public static final String RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO = "Restaurante com id informado não encontrado.";
    /**
     *
     */
    public static final String FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_ENCONTRADA = "Forma de pagamento com id informado não encontrada.";
    /**
     *
     */
    private final RestauranteRepository restauranteRepository;
    /**
     *
     */
    private final FormasPagamentoRepository formasPagamentoRepository;
    /**
     *
     */
    private final FormasPagamentoOUTMapper formasPagamentoOUTMapper;

    @Transactional(readOnly = true)
    public Set<FormasPagamentoOUT> buscaFormasPagamento(Long restauranteId) {
        return formasPagamentoOUTMapper.toDto(restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntityNotFoundException(RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO)).getFormasPagamento());
    }

    @Transactional
    public void adicionaFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntityNotFoundException(RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO));
        var formaPagamento = formasPagamentoRepository.findById(formaPagamentoId).orElseThrow(
                () -> new EntityNotFoundException(FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_ENCONTRADA));
        restaurante.adicionaFormaPagamento(formaPagamento);
    }

    @Transactional
    public void removeFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntityNotFoundException(RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO));
        var formaPagamento = formasPagamentoRepository.findById(formaPagamentoId).orElseThrow(
                () -> new EntityNotFoundException(FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_ENCONTRADA));
        restaurante.removeFormaPagamento(formaPagamento);
    }
}
