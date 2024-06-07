package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Pedido;
import com.ferry.myifood.domain.model.dto.VendaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("select new com.ferry.myifood.domain.model.dto.VendaDiaria(" +
            "p.dataCriacao, count(p.id), sum(p.valorTotal)" +
            ") from Pedido p where p.status in ('CONFIRMADO', 'ENTREGUE')" +
            "and p.dataCriacao between :dataInicio and :dataFim group by p.dataCriacao")
    List<VendaDiaria> totalVendidoPorData(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}