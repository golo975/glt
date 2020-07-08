package com.gaolong.springbootdemo1.controller;

import com.gaolong.springbootdemo1.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final User myUser;

    public HelloController(User myUser) {
        this.myUser = myUser;
    }

    @RequestMapping("/hello")
    private String hello() {
        return myUser.toString();
    }

}
