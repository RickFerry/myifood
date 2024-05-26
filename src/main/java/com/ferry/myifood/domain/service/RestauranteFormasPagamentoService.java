package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.FormasPagamentoNaoEncontradaException;
import com.ferry.myifood.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.myifood.domain.mapper.formapagamento.FormasPagamentoOUTMapper;
import com.ferry.myifood.domain.model.dto.output.FormasPagamentoOUT;
import com.ferry.myifood.domain.repository.FormasPagamentoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import com.ferry.myifood.domain.utils.ConstantsUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

import static com.ferry.myifood.domain.service.RestauranteProdutoService.RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO;
import static com.ferry.myifood.domain.utils.ConstantsUtil.FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE;
import static com.ferry.myifood.domain.utils.ConstantsUtil.RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class RestauranteFormasPagamentoService {
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
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE)).getFormasPagamento());
    }

    @Transactional
    public void adicionaFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO));
        var formaPagamento = formasPagamentoRepository.findById(formaPagamentoId).orElseThrow(
                () -> new FormasPagamentoNaoEncontradaException(formaPagamentoId, FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE));
        restaurante.adicionaFormaPagamento(formaPagamento);
    }

    @Transactional
    public void removeFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO));
        var formaPagamento = formasPagamentoRepository.findById(formaPagamentoId).orElseThrow(
                () -> new FormasPagamentoNaoEncontradaException(formaPagamentoId, FORMA_DE_PAGAMENTO_COM_ID_INFORMADO_NAO_EXISTE));
        restaurante.removeFormaPagamento(formaPagamento);
    }
}
