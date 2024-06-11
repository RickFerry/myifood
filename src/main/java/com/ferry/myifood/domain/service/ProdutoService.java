package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.exception.ProdutoNaoEncontradoException;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ferry.myifood.domain.utils.ConstantsUtil.NAO_EXISTE_PRODUTO_COM_ESTE_ID_VINCULADO_A_ESTE_RESTAURANTE;

@Service
@AllArgsConstructor
public class ProdutoService {
    /**
     *
     */
    private final ProdutoRepository produtoRepository;

    /**
     * @return List<Produto>
     */
    @Transactional(readOnly = true)
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    /**
     * @param id
     * @return Produto
     */
    @Transactional(readOnly = true)
    public Produto buscar(final Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ProdutoNaoEncontradoException(id, NAO_EXISTE_PRODUTO_COM_ESTE_ID_VINCULADO_A_ESTE_RESTAURANTE));
    }
}
