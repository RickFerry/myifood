package com.ferry.myifood.domain.model.dtos;

import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Restaurante}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDto {
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