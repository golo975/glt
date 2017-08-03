package com.gaolong.tageverything.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class ControllerTest {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model){
        return "index";
    }
}
