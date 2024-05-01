package com.ferry.myifood.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {
    /**
     *
     */
    @NotBlank
    private Long id;
    /**
     *
     */
    @NotBlank
    private String nome;
    /**
     *
     */
    @NotBlank
    private String descricao;
    /**
     *
     */
    @NotBlank
    private BigDecimal preco;
    /**
     *
     */
    @NotBlank
    private Boolean ativo;
}
