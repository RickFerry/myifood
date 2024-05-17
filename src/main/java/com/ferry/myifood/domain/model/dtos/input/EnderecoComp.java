package com.ferry.myifood.domain.model.dtos.input;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoComp {
	/**
	 *
	 */
	@NotNull
	private String logradouro;
	/**
	 *
	 */
	@NotNull
	private String numero;
	/**
	 *
	 */
	@NotNull
	private String complemento;
	/**
	 *
	 */
	@NotNull
	private String bairro;
	/**
	 *
	 */
	@NotNull
	private String cep;
	/**
	 *
	 */
	@Valid
	@NotNull
	private CidadeComp cidadeComp;
}