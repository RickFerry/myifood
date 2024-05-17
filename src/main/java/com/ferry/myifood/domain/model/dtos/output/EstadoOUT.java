package com.ferry.myifood.domain.model.dtos.output;

import lombok.*;

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