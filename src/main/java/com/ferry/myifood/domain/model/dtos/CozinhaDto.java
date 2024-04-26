package com.ferry.myifood.domain.model.dtos;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cozinha}
 */
@Value
public class CozinhaDto implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String nome;
}