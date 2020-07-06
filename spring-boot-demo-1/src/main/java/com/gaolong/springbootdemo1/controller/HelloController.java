package com.gaolong.springbootdemo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {

    @RequestMapping("/")
    private String hello() {
        return "hello spring boot";
    }

}
