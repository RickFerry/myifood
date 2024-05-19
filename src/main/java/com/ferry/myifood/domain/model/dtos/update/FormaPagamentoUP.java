package com.ferry.myifood.domain.model.dtos.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoUP implements Serializable {
    /**
     *
     */
    @NotBlank
    String descricao;
}