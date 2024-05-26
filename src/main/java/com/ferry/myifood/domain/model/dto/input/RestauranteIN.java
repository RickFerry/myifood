package com.ferry.myifood.domain.model.dto.input;

import com.ferry.myifood.domain.model.dto.complemento.CozinhaComp;
import com.ferry.myifood.domain.model.dto.complemento.EnderecoComp;
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
	@NotNull
	private Boolean aberto;
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
	private CozinhaComp cozinha;
}