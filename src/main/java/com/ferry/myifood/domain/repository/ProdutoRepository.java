package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}