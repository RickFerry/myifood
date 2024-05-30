package com.ferry.myifood.domain.model.dto.complemento;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioComp {
	@NotNull
	private Long id;
}