package com.ferry.myifood.domain.model.dtos;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Restaurante}
 */
@Value
public class RestauranteDto implements Serializable {
    Long id;
    String nome;
    BigDecimal taxaFrete;
    String cepEndereco;
    String logradouroEndereco;
    String numeroEndereco;
    String complementoEndereco;
    String bairroEndereco;
    CozinhaDto cozinha;
    Set<ProdutoDto> produtos;
    Set<FormaPagamentoDto> formasPagamento;
}