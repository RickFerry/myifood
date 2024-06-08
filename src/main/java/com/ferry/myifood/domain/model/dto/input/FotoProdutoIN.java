package com.ferry.myifood.domain.model.dto.input;

import com.ferry.myifood.domain.config.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoIN {
    @NotNull
    @FileSize(max = "500KB")
    private MultipartFile arquivo;
    @NotBlank
    private String descricao;
}
