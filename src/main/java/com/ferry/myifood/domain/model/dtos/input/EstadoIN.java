package com.ferry.myifood.domain.model.dtos.input;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoIN {
    /**
     *
     */
    @NotBlank
    private String nome;
}
