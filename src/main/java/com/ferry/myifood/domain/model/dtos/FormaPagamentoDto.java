package com.ferry.myifood.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * DTO for {@link com.ferry.myifood.domain.model.FormaPagamento}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoDto {
    Long id;
    @NotBlank
    String descricao;
}