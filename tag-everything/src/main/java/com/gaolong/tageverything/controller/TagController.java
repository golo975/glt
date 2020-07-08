package com.gaolong.tageverything.controller;

import com.gaolong.tageverything.model.tag.Tag;
import com.gaolong.tageverything.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author gaolong
 */
@RestController("/test")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "index";
    }

    @RequestMapping("/getTag")
    @ResponseBody
    public Tag getUser(Tag tag){
        System.out.println(tag);
        return tag;
    }

    @RequestMapping("/postTag")
    @ResponseBody
    public Tag postUser(@RequestBody Tag tag){
        System.out.println(tag);

        Tag rs = new Tag();
        rs.setId(2L);
        rs.setName("test2");
        return rs;
    }

}
