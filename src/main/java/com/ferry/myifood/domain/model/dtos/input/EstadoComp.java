package com.ferry.myifood.domain.model.dtos.input;

import com.ferry.myifood.domain.model.group.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Estado}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoComp {
    @NotNull(groups = Groups.EstadoId.class)
    Long id;
}