package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
