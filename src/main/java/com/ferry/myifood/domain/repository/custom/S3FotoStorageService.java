package com.ferry.myifood.domain.repository.custom;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ferry.myifood.domain.exception.StorageException;
import com.ferry.myifood.domain.model.storage.StorageProperties;
import lombok.AllArgsConstructor;

import java.net.URL;

@AllArgsConstructor
public class S3FotoStorageService implements FotoStorageService {
    private final AmazonS3 amazonS3;
    private final StorageProperties storageProperties;

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String path = getCaminhoArquivo(novaFoto.getNomeArquivo());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(novaFoto.getContentType());

            PutObjectRequest objectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(), path, novaFoto.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(objectRequest);
        } catch (SdkClientException e) {
            throw new StorageException("Não foi possível armazenar o arquivo", e);
        }
    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String path = getCaminhoArquivo(nomeArquivo);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), path);
        return FotoRecuperada.builder()
                .url(url.toString())
                .build();
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            String path = getCaminhoArquivo(nomeArquivo);
            amazonS3.deleteObject(storageProperties.getS3().getBucket(), path);
        } catch (SdkClientException e) {
            throw new StorageException("Não foi possível excluir o arquivo", e);
        }
    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getPhotoDirectory(), nomeArquivo);
    }
}
