package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoOUT{
	private String logradouro;
	private String complemento;
	private String numero;
	private String bairro;
	private String cep;
	private CidadeOUT cidade;
}