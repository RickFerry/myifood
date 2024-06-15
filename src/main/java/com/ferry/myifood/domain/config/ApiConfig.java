package com.ferry.myifood.domain.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferry.myifood.domain.model.storage.StorageProperties;
import com.ferry.myifood.domain.repository.custom.FotoStorageService;
import com.ferry.myifood.domain.repository.custom.LocalFotoStorageService;
import com.ferry.myifood.domain.repository.custom.S3FotoStorageService;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApiConfig {
    private final StorageProperties storageProperties;

    @Bean
    FilterRegistrationBean<SquigglyRequestFilter> filterRegistrationBean(ObjectMapper objectMapper) {
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider("fields", null));
        FilterRegistrationBean<SquigglyRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new SquigglyRequestFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    AmazonS3 amazonS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(
                storageProperties.getS3().getAccessKey(), storageProperties.getS3().getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    FotoStorageService fotoStorageService(AmazonS3 amazonS3) {
        return storageProperties.getTipo().equals(StorageProperties.StorageType.S3)
                ? new S3FotoStorageService(amazonS3, storageProperties)
                : new LocalFotoStorageService(storageProperties);
    }
}
