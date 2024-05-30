package com.ferry.myifood.domain.model.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoCompOut {
	private Long id;
	@JsonIgnore
	private String nome;
	@JsonIgnore
	private String descricao;
	@JsonIgnore
	private BigDecimal preco;
	@JsonIgnore
	private Boolean ativo;
}