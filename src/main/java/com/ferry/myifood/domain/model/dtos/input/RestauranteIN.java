package com.ferry.myifood.domain.model.dtos.input;

import com.ferry.myifood.domain.model.dtos.complemento.CozinhaComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

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
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	/**
	 *
	 */
	@NotNull
	private Boolean ativo;
	/**
	 *
	 */
	@Valid
	@NotNull
	private CozinhaComp cozinha;
}