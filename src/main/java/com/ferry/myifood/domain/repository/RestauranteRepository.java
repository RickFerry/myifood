package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    @Query("select produtos from Restaurante r inner join r.produtos produtos where produtos.ativo = true and r.id = :id")
    Set<Produto>  buscaProdutosAtivosPorId(@Param("id") Long id);
}
