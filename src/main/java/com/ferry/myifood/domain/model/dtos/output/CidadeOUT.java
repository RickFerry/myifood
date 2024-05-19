package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeOUT{
	/**
	 *
	 */
	private Long id;
	/**
	 *
	 */
	private String nome;
	/**
	 *
	 */
	private EstadoOUT estado;
}