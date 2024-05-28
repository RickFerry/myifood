package com.ferry.myifood.domain.model.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDetalhe {
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
	private String email;
}