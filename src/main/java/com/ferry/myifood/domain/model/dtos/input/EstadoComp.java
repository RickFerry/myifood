package com.ferry.myifood.domain.model.dtos.input;

import com.ferry.myifood.domain.model.group.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Estado}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoComp implements Serializable {
    @NotNull(groups = Groups.EstadoId.class)
    Long id;
}