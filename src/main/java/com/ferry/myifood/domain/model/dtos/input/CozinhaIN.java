package com.ferry.myifood.domain.model.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cozinha}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaIN {
    @NotBlank
    String nome;
}