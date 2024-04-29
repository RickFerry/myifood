package com.ferry.myifood.domain.model.dtos.input;

import com.ferry.myifood.domain.model.group.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeIN {
    @NotBlank
    String nome;
    @Valid
    @NotNull
    @ConvertGroup(to = Groups.EstadoId.class)
    EstadoComp estado;
}