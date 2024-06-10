package com.ferry.myifood.domain.repository.custom;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    void armazenar(NovaFoto novaFoto);

    void remover(String nomeArquivo);

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID() + "_" + nomeOriginal;
    }

    default void substituir(NovaFoto novaFoto, String nomeAntigoArquivo) {
        this.armazenar(novaFoto);
        if (nomeAntigoArquivo != null) {
            this.remover(nomeAntigoArquivo);
        }
    }

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;
    }
}
