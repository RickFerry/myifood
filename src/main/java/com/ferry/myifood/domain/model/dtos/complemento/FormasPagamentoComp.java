package com.ferry.myifood.domain.model.dtos.complemento;

import lombok.*;

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