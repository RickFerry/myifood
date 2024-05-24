package com.ferry.myifood.domain.model.dto.input;

import com.ferry.myifood.domain.model.dto.complemento.PermissaoComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoIN {
    /**
     *
     */
    @NotBlank
    private String nome;
    /**
     *
     */
    @Valid
    @NotNull
    private Set<PermissaoComp> permissoes;
}
