package com.ferry.myifood.domain.model.dtos.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeUP {
    @NotBlank
    String nome;
}