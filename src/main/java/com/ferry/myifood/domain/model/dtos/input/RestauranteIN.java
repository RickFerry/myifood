package com.ferry.myifood.domain.model.dtos.input;

import java.math.BigDecimal;
import java.util.Set;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteIN{
	/**
	 *
	 */
	@NotBlank
	private String nome;
	/**
	 *
	 */
	@PositiveOrZero
	private BigDecimal taxaFrete;
	/**
	 *
	 */
	@Valid
	@NotNull
	private EnderecoComp endereco;
	/**
	 *
	 */
	@Valid
	@NotNull
	private CozinhaComp cozinhaComp;
	/**
	 *
	 */
	@Valid
	@NotNull
	private Set<FormasPagamentoComp> formasPagamentoComp;
}