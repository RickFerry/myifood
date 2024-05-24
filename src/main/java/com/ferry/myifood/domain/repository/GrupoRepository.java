package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}