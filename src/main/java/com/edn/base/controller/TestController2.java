package com.edn.base.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste2")
public class TestController2 {

    @Value("${teste.url}")
    private String variavel;

    @GetMapping
    public void teste(@RequestParam String id) {
        System.out.println(variavel);
    }

}
