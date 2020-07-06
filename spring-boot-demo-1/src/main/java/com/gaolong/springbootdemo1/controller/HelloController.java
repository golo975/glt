package com.gaolong.springbootdemo1.controller;

import com.gaolong.springbootdemo1.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private User user;

    @RequestMapping("/hello")
    private String hello() {
//        return "hello spring boot";
        return user.toString();
    }

}
