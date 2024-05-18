package com.ferry.myifood.domain.model.dtos.complemento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormasPagamentoComp {
	/**
	 *
	 */
	@NotNull
	private Long id;
}