package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GruposRepository extends JpaRepository<Grupo, Long> {
}