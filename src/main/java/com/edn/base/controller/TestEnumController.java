package com.edn.base.controller;

import com.edn.base.models.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test-enum")
public class TestEnumController {

    @PostMapping
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        System.out.println(account);
        return ResponseEntity.ok("SUCCESS");
    }

}
