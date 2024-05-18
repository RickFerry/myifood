package com.ferry.myifood.domain.model.dtos.input;

import com.ferry.myifood.domain.model.dtos.complemento.EstadoComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeIN {
    /**
     *
     */
    @NotBlank
    private String nome;
    /**
     *
     */
    @Valid
    @NotNull
    private EstadoComp estado;
}
