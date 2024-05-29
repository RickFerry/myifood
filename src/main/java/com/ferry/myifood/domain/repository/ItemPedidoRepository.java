package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}