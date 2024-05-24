package com.ferry.myifood.domain.model.dto.update;

import com.ferry.myifood.domain.model.dto.complemento.GrupoComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUP {
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
	@Valid
	@NotNull
	private List<GrupoComp> grupos;
}