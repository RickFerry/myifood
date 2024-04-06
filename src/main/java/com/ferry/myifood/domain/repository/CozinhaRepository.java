package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}