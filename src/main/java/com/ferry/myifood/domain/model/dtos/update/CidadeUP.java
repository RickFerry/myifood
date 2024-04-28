package com.ferry.myifood.domain.model.dtos.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeUP implements Serializable {
    @NotBlank
    String nome;
}