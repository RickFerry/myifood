package com.ferry.myifood.domain.model.dto.input;

import com.ferry.myifood.domain.model.dto.complemento.CidadeComp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoIN {
    /**
     *
     */
    @NotBlank
    private String cep;
    /**
     *
     */
    @NotBlank
    private String logradouro;
    /**
     *
     */
    @NotBlank
    private String numero;
    /**
     *
     */
    @NotBlank
    private String complemento;
    /**
     *
     */
    @NotBlank
    private String bairro;
    /**
     *
     */
    @Valid
    @NotNull
    private CidadeComp cidade;
}
