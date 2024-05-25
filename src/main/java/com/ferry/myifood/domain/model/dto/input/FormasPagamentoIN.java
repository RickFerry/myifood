package com.ferry.myifood.domain.model.dto.input;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormasPagamentoIN {
    /**
     *
     */
    @NotBlank
    String descricao;
}