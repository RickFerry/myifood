package com.ferry.myifood.domain.model.dto.complemento;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoComp {
	/**
	 *
	 */
	@NotNull
	@PositiveOrZero
	private Long id;
}