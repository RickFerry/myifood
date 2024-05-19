package com.ferry.myifood.domain.model.dtos.input;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoIN {
    /**
     *
     */
    @NotBlank
    String descricao;
}