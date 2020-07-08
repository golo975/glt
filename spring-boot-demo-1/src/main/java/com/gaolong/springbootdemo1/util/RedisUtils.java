package com.gaolong.springbootdemo1.util;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisUtils {

    private static StringRedisTemplate stringRedisTemplate;

    public RedisUtils(StringRedisTemplate stringRedisTemplate) {
        RedisUtils.stringRedisTemplate = stringRedisTemplate;
    }

    public static void executePipelined(RedisCallback<?> action) {
        stringRedisTemplate.executePipelined(action);
    }

    public static <T> void execute(RedisCallback<T> action) {
        stringRedisTemplate.execute(action);
    }

    public static List<Object> transaction(Runnable runnable) {
        stringRedisTemplate.setEnableTransactionSupport(true);
        stringRedisTemplate.multi();
        runnable.run();
        List<Object> exec = stringRedisTemplate.exec();
        stringRedisTemplate.setEnableTransactionSupport(false);
        return exec;
    }


    public static class STRING {

    }

    public static class BITMAP {
        public static Boolean getBit(String key, int offset) {
            return stringRedisTemplate.opsForValue().getBit(key, offset);
        }

        public static Boolean setBit(String key, int offset, boolean value) {
            return stringRedisTemplate.opsForValue().setBit(key, offset, value);
        }
    }

    public static class HASH {

    }

    public static class SSET {

    }

    public static class LIST {

    }

    public static StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }
}
