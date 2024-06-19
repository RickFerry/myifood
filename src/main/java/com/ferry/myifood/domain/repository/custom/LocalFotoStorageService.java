package com.ferry.myifood.domain.repository.custom;

import com.ferry.myifood.domain.exception.StorageException;
import com.ferry.myifood.domain.model.storage.StorageProperties;
import lombok.AllArgsConstructor;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;

@AllArgsConstructor
public class LocalFotoStorageService implements FotoStorageService {
    private StorageProperties storageProperties;

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path path = getArquivoPath(novaFoto.getNomeArquivo());
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(path));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar o arquivo", e);
        }
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            Path path = getArquivoPath(nomeArquivo);
            return FotoRecuperada.builder()
                    .inputStream(newInputStream(path))
                    .url(path.toUri().toURL().toString())
                    .build();
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path path = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(path);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir o arquivo", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return storageProperties.getLocal().getLocalDirectory().resolve(Path.of(nomeArquivo));
    }
}
