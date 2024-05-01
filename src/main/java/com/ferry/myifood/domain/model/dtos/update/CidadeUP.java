package com.ferry.myifood.domain.model.dtos.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeUP {
    /**
     *
     */
    @NotBlank
    private String nome;
}
