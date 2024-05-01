package com.ferry.myifood.domain.model.dtos.input;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoComp {
    /**
     *
     */
    @NotNull
    private Long id;
}
