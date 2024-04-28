package com.ferry.myifood.domain.model.dtos.output;

import com.ferry.myifood.domain.model.dtos.EstadoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeOUT implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String nome;
    @NotNull
    EstadoDto estado;
}