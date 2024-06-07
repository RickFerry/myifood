package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.dto.VendaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("select new com.ferry.myifood.domain.model.dto.VendaDiaria(" +
            "p.dataCriacao, count(p.id), sum(p.valorTotal)) from Pedido p group by p.dataCriacao")
    List<VendaDiaria> totalVendidoPorData();
}