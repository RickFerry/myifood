package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.repository.custom.ProdutoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryCustom {
}
