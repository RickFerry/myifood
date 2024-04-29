package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cozinha}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CozinhaOUT {
    Long id;
    String nome;
}