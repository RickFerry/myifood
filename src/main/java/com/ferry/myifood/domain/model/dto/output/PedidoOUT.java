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
    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private EnderecoOUT enderecoEntrega;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataACaminho;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;
    private FormasPagamentoOUT formaPagamento;
    private RestauranteOUT restaurante;
    private UsuarioOUT cliente;
    private Set<ItemPedidoOUT> itens;
}