package com.ferry.myifood.domain.model.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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
