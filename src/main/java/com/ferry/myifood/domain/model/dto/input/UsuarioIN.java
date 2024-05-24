package com.ferry.myifood.domain.model.dto.input;

import java.util.List;

import com.ferry.myifood.domain.model.dto.complemento.GrupoComp;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioIN{
	/**
	 *
	 */
	@NotBlank
	private String nome;
	/**
	 *
	 */
	@NotBlank
	private String email;
	/**
	 *
	 */
	@NotBlank
	private String senha;
	/**
	 *
	 */
	@Valid
	@NotNull
	private List<GrupoComp> grupos;
}