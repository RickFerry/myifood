package com.ferry.myifood.domain.model.dtos.input;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Estado}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoIN {
    @NotBlank
    String nome;
}