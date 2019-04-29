package io.github.iodar.rest.v1.controller.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("testcontainers.postgres")
public class PostgresContainerConfiguration {
    private String image;
    private String database;
    private String user;
    private String password;
    private Integer port;
}
