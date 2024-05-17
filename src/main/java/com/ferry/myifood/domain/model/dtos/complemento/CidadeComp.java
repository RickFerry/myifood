package com.ferry.myifood.domain.model.dtos.complemento;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeComp{
	/**
	 *
	 */
	@PositiveOrZero
	private Long id;
	/**
	 *
	 */
	@Valid
	@NotNull
	private EstadoComp estado;
}