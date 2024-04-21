package com.ferry.myifood.domain.model;

import com.ferry.myifood.domain.model.group.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @NotBlank
    @Column(name = "endereco_cep")
    private String cep;
    @NotBlank
    @Column(name = "endereco_logradouro")
    private String logradouro;
    @NotBlank
    @Column(name = "endereco_numero")
    private String numero;
    @Column(name = "endereco_complemento")
    private String complemento;
    @NotBlank
    @Column(name = "endereco_bairro")
    private String bairro;

    @Valid
    @NotNull
    @ManyToOne
    @ConvertGroup(to = Groups.CidadeId.class)
    private Cidade cidade;
}
