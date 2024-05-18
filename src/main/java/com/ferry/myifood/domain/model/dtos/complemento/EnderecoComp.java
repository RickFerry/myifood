package com.ferry.myifood.domain.model.dtos.complemento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoComp {
	/**
	 *
	 */
	@NotBlank
	private String logradouro;
	/**
	 *
	 */
	@NotBlank
	private String numero;
	/**
	 *
	 */
	@NotBlank
	private String complemento;
	/**
	 *
	 */
	@NotBlank
	private String bairro;
	/**
	 *
	 */
	@NotBlank
	private String cep;
	/**
	 *
	 */
	@Valid
	@NotNull
	private CidadeComp cidade;
}