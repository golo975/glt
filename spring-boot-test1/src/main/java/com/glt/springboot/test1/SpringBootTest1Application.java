package com.glt.springboot.test1;

import com.glt.dubbo.test.api.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
@RequestMapping("/test1")
@Configuration
@ImportResource(value = {"classpath*:echo-consumer.xml"})
public class SpringBootTest1Application {

    @Autowired
    private EchoService echoService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTest1Application.class, args);
    }

    @RequestMapping("/")
    public String test() {
        return echoService.echo("");
//        return ";";
    }
}
