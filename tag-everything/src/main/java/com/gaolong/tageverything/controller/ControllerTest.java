package com.gaolong.tageverything.controller;

import com.gaolong.tageverything.model.tag.Tag;
import com.gaolong.tageverything.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class ControllerTest {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "index";
    }

    @RequestMapping(value = "/handle41")
    public String handle41(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "success";
    }

    @RequestMapping("/handle51")
    public ResponseEntity<Tag> handle51(HttpEntity<Tag> requestEntity) {
        Tag tag = requestEntity.getBody();
        tag.setId(1);
        tag.setName("test");
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }


}
