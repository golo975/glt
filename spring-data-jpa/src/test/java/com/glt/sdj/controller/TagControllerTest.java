package com.glt.sdj.controller;

import com.alibaba.fastjson.JSONObject;
import com.glt.sdj.model.DO.Tag;
import com.glt.sdj.model.VO.TagPageVO;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findByPageTest() {
        TagPageVO tagPageVO = restTemplate.getForObject("/page/0", TagPageVO.class);
        System.out.println(tagPageVO);
    }

    @Test
    public void addTest() {

        JSONObject reqVO = new JSONObject(10);
        reqVO.put("name", "标签名");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonPost = reqVO.toString();
        HttpEntity<String> entity = new HttpEntity<>(jsonPost, headers);

        Tag tag = restTemplate.postForObject("/page", entity, Tag.class);
        System.out.println(tag);
    }

    // TODO: 2017/12/24 在运行测试的时候为什么不进断点？？？插入和获取Tag的2个方法都没有成功！！
}
