package com.brucecompiler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class BruceController {

    @GetMapping
    public String hello() {
        return "Hello Bruce Compiler file-service";
    }
}
