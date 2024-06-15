package com.ferry.myifood.domain.model.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Setter
@Getter
@Component
@ConfigurationProperties("myifood.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();
    private StorageType tipo = StorageType.LOCAL;

    public enum StorageType {
        LOCAL, S3
    }

    @Getter
    @Setter
    public static class Local {
        private Path localDirectory;
    }

    @Getter
    @Setter
    public static class S3 {
        private String bucket;
        private String accessKey;
        private String secretKey;
        private Regions region;
        private String photoDirectory;
    }
}
