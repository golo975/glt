package com.gaolong.springbootdemo1;

import com.gaolong.springbootdemo1.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private User user;

    @Test
    public void Test() {
        System.out.println(user);
        System.out.println("Hello World!");
    }

    /**
     * spring boot 1.5.x 中 redis 客户端默认为 Jedis
     * spring boot 2.x 中 redis 客户端默认为 Lettuce
     */
    @Test
    public void redisTest() {
        String key = String.valueOf(System.currentTimeMillis());

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        StreamOperations<String, Object, Object> streamOperations = stringRedisTemplate.opsForStream();

        HyperLogLogOperations<String, String> hyperLogLogOperations = stringRedisTemplate.opsForHyperLogLog();

        // todo 对 bitmap 怎么处理？


        BoundHashOperations<String, Object, Object> boundHashOperations = stringRedisTemplate.boundHashOps(key);
    }
}
