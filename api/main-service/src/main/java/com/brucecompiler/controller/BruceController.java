package com.brucecompiler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")
public class BruceController {

    @GetMapping
    public String hello() {
        return "Hello main service";
    }
}
