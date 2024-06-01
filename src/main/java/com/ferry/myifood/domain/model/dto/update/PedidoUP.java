package com.ferry.myifood.domain.model.dto.update;

import com.ferry.myifood.domain.model.dto.complemento.EnderecoComp;
import com.ferry.myifood.domain.model.dto.complemento.FormasPagamentoComp;
import com.ferry.myifood.domain.model.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoUP{
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	@NotNull
	@PositiveOrZero
	private BigDecimal subtotal;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorTotal;
	@NotNull
	private LocalDateTime dataCriacao;
	@NotNull
	private LocalDateTime dataConfirmacao;
	@NotNull
	private LocalDateTime dataACaminho;
	@NotNull
	private LocalDateTime dataEntrega;
	@NotNull
	private LocalDateTime dataCancelamento;
	@NotNull
	private StatusPedido status;
	@Valid
	@NotNull
	private EnderecoComp enderecoEntrega;
	@Valid
	@NotNull
	private FormasPagamentoComp formaPagamento;
}