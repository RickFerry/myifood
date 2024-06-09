package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.FotoProduto;
import com.ferry.myifood.domain.model.Produto;
import com.ferry.myifood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    @Query("select produtos from Restaurante r inner join r.produtos produtos where produtos.ativo = true and r.id = :id")
    Set<Produto> buscaProdutosAtivosPorId(@Param("id") Long id);

    @Query("select p from Restaurante r join r.produtos p where r.id = :restauranteId and p.id = :produtoId")
    Optional<Produto> findProdutoByRestauranteIdAndProdutoId(
            @Param("restauranteId") Long restauranteId, @Param("produtoId") Long produtoId);

    @Query("select f from Restaurante r join r.produtos p join p.fotoProduto f where r.id = :restauranteId and p.id = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}
