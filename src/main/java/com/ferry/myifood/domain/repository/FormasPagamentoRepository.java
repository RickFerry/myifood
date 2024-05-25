package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormasPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}