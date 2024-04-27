package com.ferry.myifood.domain.model.dtos;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.FormaPagamento}
 */
@Value
public class FormaPagamentoDto implements Serializable {
    Long id;
    @NotBlank
    String descricao;
}