package com.brucecompiler.controller;

import com.brucecompiler.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gateway")
public class BruceController {

    private final RedisOperator redisOperator;

    @Autowired
    public BruceController(RedisOperator redisOperator) {
        this.redisOperator = redisOperator;
    }

    @GetMapping
    public String hello() {
        redisOperator.set("hello", "world");
        return "Hello Gateway!";
    }
}
