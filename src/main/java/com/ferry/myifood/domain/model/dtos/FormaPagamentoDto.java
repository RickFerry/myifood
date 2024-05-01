package com.ferry.myifood.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoDto {
    /**
     *
     */
    private Long id;
    /**
     *
     */
    @NotBlank
    private String descricao;
}
