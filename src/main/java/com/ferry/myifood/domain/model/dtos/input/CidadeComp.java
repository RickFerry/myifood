package com.ferry.myifood.domain.model.dtos.input;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeComp{
	/**
	 *
	 */
	@NotNull
	private Long id;
	/**
	 *
	 */
	@NotNull
	@Valid
	private EstadoComp estadoComp;
}