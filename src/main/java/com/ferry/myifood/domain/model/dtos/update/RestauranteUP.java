package com.ferry.myifood.domain.model.dtos.update;

import com.ferry.myifood.domain.model.dtos.complemento.CozinhaComp;
import com.ferry.myifood.domain.model.dtos.complemento.EnderecoComp;
import com.ferry.myifood.domain.model.dtos.complemento.FormasPagamentoComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteUP{
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
	private CozinhaComp cozinha;
	/**
	 *
	 */
	@Valid
	@NotNull
	private Set<FormasPagamentoComp> formasPagamento;
}