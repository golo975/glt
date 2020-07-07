package com.gaolong.springbootdemo1.configuration;

import com.gaolong.springbootdemo1.bean.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    @ConfigurationProperties("myuser")
    public User myUser() {
        return new User();
    }
}
