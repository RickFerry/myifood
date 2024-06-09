package com.ferry.myifood.domain.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoOUT {
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
