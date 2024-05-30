package com.ferry.myifood.domain.model.dto.input;

import com.ferry.myifood.domain.model.dto.complemento.*;
import com.ferry.myifood.domain.model.enums.StatusPedido;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoIN{
	@NotNull
	@Positive
	private BigDecimal subtotal;
	@NotNull
	@Positive
	private BigDecimal taxaFrete;
	@NotNull
	@Positive
	private BigDecimal valorTotal;
	@NotNull
	private LocalDateTime dataCriacao;
	@NotNull
	private LocalDateTime dataConfirmacao;
	@NotNull
	private LocalDateTime dataCancelamento;
	@NotNull
	private LocalDateTime dataEntrega;
	@NotBlank
	private StatusPedido status;
	@Valid
	@NotNull
	private EnderecoComp enderecoEntrega;
	@Valid
	@NotNull
	private Set<ItemPedidoComp> itensPedido;
	@Valid
	@NotNull
	private UsuarioComp cliente;
	@Valid
	@NotNull
	private RestauranteComp restaurante;
	@Valid
	@NotNull
	private FormasPagamentoComp formaPagamento;
}