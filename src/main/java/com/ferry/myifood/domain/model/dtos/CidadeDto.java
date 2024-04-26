package com.ferry.myifood.domain.model.dtos;

import com.ferry.myifood.domain.model.Cidade;
import com.ferry.myifood.domain.model.Estado;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.ferry.myifood.domain.model.Cidade}
 */
@Value
public class CidadeDto implements Serializable {
    @NotNull
    Long id;
    @NotBlank
    String nome;
    @NotNull
    EstadoDto estado;

    public CidadeDto(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.estado = new EstadoDto(cidade.getEstado());
    }
}