package com.ferry.myifood.domain.model.dtos;

import com.ferry.myifood.domain.model.dtos.output.CozinhaOUT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDto {
    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String nome;
    /**
     *
     */
    private BigDecimal taxaFrete;
    /**
     *
     */
    private String cepEndereco;
    /**
     *
     */
    private String logradouroEndereco;
    /**
     *
     */
    private String numeroEndereco;
    /**
     *
     */
    private String complementoEndereco;
    /**
     *
     */
    private String bairroEndereco;
    /**
     *
     */
    private CozinhaOUT cozinha;
    /**
     *
     */
    private Set<ProdutoDto> produtos;
    /**
     *
     */
    private Set<FormaPagamentoDto> formasPagamento;
}
