package com.ferry.myifood.domain.repository.custom;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    void armazenar(NovaFoto novaFoto);

    FotoRecuperada recuperar(String nomeArquivo);

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
        private String contentType;
        private InputStream inputStream;
    }

    @Getter
    @Builder
    class FotoRecuperada {
        private InputStream inputStream;
        private String url;

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return inputStream != null;
        }
    }
}
