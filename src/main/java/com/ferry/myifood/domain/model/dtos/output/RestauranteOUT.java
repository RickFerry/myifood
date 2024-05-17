package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteOUT{
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private EnderecoOUT endereco;
	private CozinhaOUT cozinha;
	private Set<FormasPagamentoOUT> formasPagamento;
}