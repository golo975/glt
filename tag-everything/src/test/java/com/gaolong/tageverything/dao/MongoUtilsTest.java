package com.gaolong.tageverything.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml", "classpath:application.xml"})
public class MongoUtilsTest {

    @Test
    public void saveCommentTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("field0", 0);
        map.put("field1", "hello");
        map.put("field2", false);
        map.put("field3", 3.14);
//        MongoUtils.saveComment(map);
    }
}
