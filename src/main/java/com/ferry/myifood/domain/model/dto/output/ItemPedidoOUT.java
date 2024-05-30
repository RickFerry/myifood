package com.ferry.myifood.domain.model.dto.output;

import com.ferry.myifood.domain.model.ItemPedido;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoOUT {
    Long id;
    BigDecimal precoUnitario;
    BigDecimal precoTotal;
    Integer quantidade;
    String observacao;
    ProdutoOUT produto;
}