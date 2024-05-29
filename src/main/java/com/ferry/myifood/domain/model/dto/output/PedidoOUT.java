package com.ferry.myifood.domain.model.dto.output;

import com.ferry.myifood.domain.model.enums.StatusPedido;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoOUT {
    Long id;
    BigDecimal subtotal;
    BigDecimal taxaFrete;
    BigDecimal valorTotal;
    EnderecoOUT enderecoEntrega;
    StatusPedido status;
    LocalDateTime dataCriacao;
    LocalDateTime dataConfirmacao;
    LocalDateTime dataCancelamento;
    LocalDateTime dataEntrega;
    FormasPagamentoOUT formaPagamento;
    RestauranteOUT restaurante;
    UsuarioOUT cliente;
    Set<ItemPedidoOUT> itens;
}