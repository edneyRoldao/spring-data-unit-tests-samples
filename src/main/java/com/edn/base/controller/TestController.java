package com.edn.base.controller;


import com.edn.base.models.EntidadeTaxa;
import com.edn.base.repositories.EntidadeTaxaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test-jpa")
public class TestController {

    @Autowired
    private EntidadeTaxaRepository repository;

    @GetMapping("save-all")
    public String saveAllItemsAtOnce() {
        List<EntidadeTaxa> list = new ArrayList<>();
        list.add(new EntidadeTaxa(1L, "edney"));
        list.add(new EntidadeTaxa(10L, "giselle"));
        list.add(new EntidadeTaxa(100L, "nadine"));
        list.add(new EntidadeTaxa(1000L, "marjorie"));
        repository.saveAll(list);

        return "success";
    }

    @GetMapping("get-all")
    public List<EntidadeTaxa> listar() {
        return repository.findAll();
    }

    @GetMapping("test-param")
    public void teste(@RequestParam String id) {
        System.out.println(id.split(",").length);
    }

    @GetMapping("list")
    public void teste2(@RequestParam List<String> ids) {
        ids.forEach(System.out::println);
    }

}
