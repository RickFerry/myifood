package com.ferry.myifood.domain.model.dtos;

import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import lombok.Value;

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
    CozinhaOUT cozinha;
    Set<ProdutoDto> produtos;
    Set<FormaPagamentoDto> formasPagamento;
}