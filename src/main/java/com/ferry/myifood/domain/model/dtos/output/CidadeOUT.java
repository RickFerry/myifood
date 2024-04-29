package com.ferry.myifood.domain.model.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeOUT {
    Long id;
    String nome;
    EstadoOUT estado;
}