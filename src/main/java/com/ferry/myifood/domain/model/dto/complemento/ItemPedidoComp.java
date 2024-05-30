package com.ferry.myifood.domain.model.dto.complemento;

import com.ferry.myifood.domain.model.dto.output.ProdutoCompOut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoComp {
	@NotNull
	@Positive
	private Integer quantidade;
	@NotBlank
	private String observacao;
	@NotNull
	@Valid
	private ProdutoCompOut produto;
}