package com.ferry.myifood.domain.model.dtos.output;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cozinha}
 */
@Value
public class CozinhaOUT implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String nome;
}