package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}