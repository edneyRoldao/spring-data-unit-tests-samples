package com.edn.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfigProperties {

    private boolean enable;
    private int total;
    private String descTeste;
    private List<String> roles;
    private String path;

}
