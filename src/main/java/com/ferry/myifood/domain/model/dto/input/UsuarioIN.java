package com.ferry.myifood.domain.model.dto.input;

import java.util.List;

import com.ferry.myifood.domain.model.dto.complemento.GrupoComp;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
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
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
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