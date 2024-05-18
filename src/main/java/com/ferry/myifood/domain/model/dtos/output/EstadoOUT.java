package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoOUT{
	/**
	 *
	 */
	@PositiveOrZero
	private Long id;
	/**
	 *
	 */
	@NotBlank
	private String nome;
}