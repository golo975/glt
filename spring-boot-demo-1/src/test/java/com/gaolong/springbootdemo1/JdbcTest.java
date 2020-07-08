package com.gaolong.springbootdemo1;

import com.gaolong.springbootdemo1.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class JdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void Test() {
        jdbcTemplate.execute("insert into user (name) values ('张三')");

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user;");
        List<User> userList = maps.stream().map(fromDB -> {
            User user = new User();
            user.setId((Long) fromDB.get("id"));
            user.setName(fromDB.get("name").toString());
            return user;
        }).collect(Collectors.toList());

        System.out.println(userList);
    }

}
