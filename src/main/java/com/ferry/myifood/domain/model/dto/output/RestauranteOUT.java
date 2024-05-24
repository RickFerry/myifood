package com.ferry.myifood.domain.model.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteOUT{
	/**
	 *
	 */
	private Long id;
	/**
	 *
	 */
	private String nome;
	/**
	 *
	 */
	private BigDecimal taxaFrete;
	/**
	 *
	 */
	private Boolean ativo;
	/**
	 *
	 */
	private EnderecoOUT endereco;
	/**
	 *
	 */
	private CozinhaOUT cozinha;
}