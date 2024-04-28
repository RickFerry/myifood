package com.ferry.myifood.domain.model.dtos.input;

import com.ferry.myifood.domain.model.group.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeIN implements Serializable {
    @NotBlank
    String nome;
    @Valid
    @NotNull
    @ConvertGroup(to = Groups.EstadoId.class)
    EstadoComp estado;
}