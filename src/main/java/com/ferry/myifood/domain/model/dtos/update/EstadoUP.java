package com.ferry.myifood.domain.model.dtos.update;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUP {
    /**
     *
     */
    @NotBlank
    private String nome;
}
