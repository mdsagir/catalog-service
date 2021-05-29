package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "welcome")
public class BookProperties {

    /**
     * A message to welcome user
     */
    private String greeting;
}
