package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.mapper.produto.ProdutoOUTMapper;
import com.ferry.myifood.domain.model.dto.output.ProdutoOUT;
import com.ferry.myifood.domain.repository.ProdutoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@AllArgsConstructor
public class RestauranteProdutoService {
    /**
     *
     */
    public static final String RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO = "Restaurante com id informado não encontrado.";
    /**
     *
     */
    public static final String PRODUTO_COM_ID_INFORMADO_NAO_ENCONTRADO = "Produto com id informado não encontrado.";
    /**
     *
     */
    private final RestauranteRepository restauranteRepository;
    /**
     *
     */
    private final ProdutoRepository produtoRepository;
    /**
     *
     */
    private final ProdutoOUTMapper produtoOUTMapper;

    @Transactional(readOnly = true)
    public Set<ProdutoOUT> buscaProdutos(Long restauranteId) {
        return produtoOUTMapper.toDto(restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntityNotFoundException(RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO)).getProdutos());
    }

    @Transactional
    public void adicionaProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntityNotFoundException(RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new EntityNotFoundException(PRODUTO_COM_ID_INFORMADO_NAO_ENCONTRADO));
        restaurante.adicionaProduto(produto);
    }

    @Transactional
    public void removeProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new EntityNotFoundException(RESTAURANTE_COM_ID_INFORMADO_NAO_ENCONTRADO));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new EntityNotFoundException(PRODUTO_COM_ID_INFORMADO_NAO_ENCONTRADO));
        restaurante.removeProduto(produto);
    }
}
