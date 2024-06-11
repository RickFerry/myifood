package com.ferry.myifood.domain.repository.custom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${myifood.storage.local.directory}")
    private Path diretorioFotos;

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
    public InputStream recuperar(String nomeArquivo) {
        try {
            if (isBlank(nomeArquivo)) {
                throw new RuntimeException("Nome do arquivo não pode ser nulo");
            }
            return newInputStream(getArquivoPath(nomeArquivo));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        return diretorioFotos.resolve(Path.of(nomeArquivo));
    }
}
