package com.ferry.myifood.domain.model.dto.input;

import com.ferry.myifood.domain.model.dto.complemento.*;
import com.ferry.myifood.domain.model.enums.StatusPedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoIN{
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;
	private StatusPedido status;
	private EnderecoComp enderecoEntrega;
	private Set<ItemPedidoComp> itensPedido;
	private UsuarioComp cliente;
	private RestauranteComp restaurante;
	private FormasPagamentoComp formaPagamento;
}