package com.ferry.myifood.domain.service;

import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Produto buscar(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Não existe produto com o id informado"));
    }
}
