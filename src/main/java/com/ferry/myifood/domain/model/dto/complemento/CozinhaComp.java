package com.ferry.myifood.domain.model.dto.complemento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaComp{
	/**
	 *
	 */
	@PositiveOrZero
	private Long id;
}