package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Estado}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoOUT {
    Long id;
    String nome;
}