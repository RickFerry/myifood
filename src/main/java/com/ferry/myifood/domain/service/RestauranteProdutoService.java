package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.ProdutoNaoEncontradoException;
import com.ferry.myifood.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.myifood.domain.mapper.produto.ProdutoOUTMapper;
import com.ferry.myifood.domain.model.dto.output.ProdutoOUT;
import com.ferry.myifood.domain.repository.ProdutoRepository;
import com.ferry.myifood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.ferry.myifood.domain.utils.ConstantsUtil.PRODUTO_COM_ID_INFORMADO_NAO_EXISTE;
import static com.ferry.myifood.domain.utils.ConstantsUtil.RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE;

@Service
@AllArgsConstructor
public class RestauranteProdutoService {
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
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE)).getProdutos());
    }

    @Transactional(readOnly = true)
    public Set<ProdutoOUT> buscaProdutosAtivos(Long restauranteId) {
        return produtoOUTMapper.toDto(restauranteRepository.buscaProdutosAtivosPorId(restauranteId));
    }

    @Transactional(readOnly = true)
    public ProdutoOUT buscaProduto(Long restauranteId, Long produtoId) {
        return produtoOUTMapper.toDto(restauranteRepository.findById(restauranteId).orElseThrow(
                        () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE))
                .getProdutos().stream().filter(produto -> produto.getId().equals(produtoId)).findFirst().orElseThrow(
                        () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE)));
    }

    @Transactional
    public void adicionaProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE));
        restaurante.adicionaProduto(produto);
    }

    @Transactional
    public void removeProduto(Long restauranteId, Long produtoId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(
                () -> new RestauranteNaoEncontradoException(restauranteId, RESTAURANTE_COM_ID_INFORMADO_NAO_EXISTE));
        var produto = produtoRepository.findById(produtoId).orElseThrow(
                () -> new ProdutoNaoEncontradoException(produtoId, PRODUTO_COM_ID_INFORMADO_NAO_EXISTE));
        restaurante.removeProduto(produto);
    }
}
