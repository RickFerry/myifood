package com.ferry.myifood.domain.model.dtos.update;

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
public class EstadoUP {
    @NotBlank
    String nome;
}