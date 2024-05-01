package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends
                    JpaRepository<Restaurante, Long> {
}
