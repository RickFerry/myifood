package com.ferry.myifood.domain.model.dtos;

import com.ferry.myifood.domain.model.Estado;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Estado}
 */
@Value
public class EstadoDto implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String nome;
}