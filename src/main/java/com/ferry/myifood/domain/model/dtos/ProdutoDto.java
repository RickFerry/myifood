package com.ferry.myifood.domain.model.dtos;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Produto}
 */
@Value
public class ProdutoDto implements Serializable {
    @NotBlank
    Long id;
    @NotBlank
    String nome;
    @NotBlank
    String descricao;
    @NotBlank
    BigDecimal preco;
    @NotBlank
    Boolean ativo;
}