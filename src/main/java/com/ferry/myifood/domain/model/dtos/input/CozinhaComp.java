package com.ferry.myifood.domain.model.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaComp{
	/**
	 *
	 */
	@NotNull
	private Long id;
}