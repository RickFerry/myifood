package com.ferry.myifood.domain.model.dto.update;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormasPagamentoUP implements Serializable {
    /**
     *
     */
    @NotBlank
    String descricao;
}