package com.glt.springboot.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/test2")
@Configuration
@ImportResource(value = {"classpath*:echo-provider.xml"})
public class SpringBootTest2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTest2Application.class, args);
    }

    @RequestMapping("/")
    public String test() {
        return "Hello World!";
    }

    @RequestMapping("/user")
    public User getUser() {
        return new User(1L, "倪升武", "123456");
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1L, "倪升武", "123456");
        User user2 = new User(2L, "达人课", "123456");
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    @RequestMapping("/map")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(3);
        User user = new User(1L, "倪升武", "123456");
        map.put("作者信息", user);
        map.put("博客地址", "http://blog.itcodai.com");
        map.put("CSDN地址", "http://blog.csdn.net/eson_15");
        map.put("粉丝数量", 4153);
        return map;
    }
}
