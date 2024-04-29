package com.ferry.myifood.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Produto}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
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