package com.edn.base.controller;

import com.edn.base.config.AppConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("test-prop")
public class TestPropertiesController {

    private final AppConfigProperties props;

    @Value("${teste.url:}")
    private String variavel;

    @GetMapping
    public String testProp() {
        return variavel;
    }

}
