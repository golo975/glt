package com.gaolong.springbootdemo1;

import com.gaolong.springbootdemo1.bean.User;
import com.gaolong.springbootdemo1.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.*;

import java.util.List;

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

    @Test
    public void RedisUtilsTest() {
        String key = String.valueOf(System.currentTimeMillis());

        System.out.println(RedisUtils.BITMAP.getBit(key, 0));
        RedisUtils.BITMAP.setBit(key, 0, true);
        System.out.println(RedisUtils.BITMAP.getBit(key, 0));
    }

    @Test
    public void transactionTest() {
        String key = String.valueOf(System.currentTimeMillis());

        List<Object> transaction = RedisUtils.transaction(() -> {
            RedisUtils.BITMAP.setBit(key, 0, true);
            RedisUtils.BITMAP.setBit(key, 1, false);
            RedisUtils.BITMAP.setBit(key, 2, true);
            RedisUtils.BITMAP.setBit(key, 3, false);
            RedisUtils.BITMAP.setBit(key, 4, true);
            RedisUtils.BITMAP.setBit(key, 5, false);
            RedisUtils.BITMAP.setBit(key, 6, true);
            RedisUtils.BITMAP.setBit(key, 7, false);


            RedisUtils.getStringRedisTemplate().opsForValue().
                    bitField(key, BitFieldSubCommands.create()
                            .get(BitFieldSubCommands.BitFieldType.UINT_8).valueAt(0));

            RedisUtils.getStringRedisTemplate().opsForValue().
                    bitField(key, BitFieldSubCommands.create()
                            .get(BitFieldSubCommands.BitFieldType.UINT_8).valueAt(1));

            RedisUtils.getStringRedisTemplate().opsForValue().
                    bitField(key, BitFieldSubCommands.create()
                            .get(BitFieldSubCommands.BitFieldType.UINT_8).valueAt(2));

            RedisUtils.getStringRedisTemplate().opsForValue().
                    bitField(key, BitFieldSubCommands.create()
                            .get(BitFieldSubCommands.BitFieldType.UINT_8).valueAt(3));

            RedisUtils.getStringRedisTemplate().opsForValue().
                    bitField(key, BitFieldSubCommands.create()
                            .get(BitFieldSubCommands.BitFieldType.UINT_8).valueAt(4));
        });

        System.out.println(transaction);

        {
            Long aLong = ((List<Long>) transaction.get(8)).get(0);
            System.out.println(Long.toBinaryString(aLong));
        }
        {
            Long aLong = ((List<Long>) transaction.get(9)).get(0);
            System.out.println(Long.toBinaryString(aLong));
        }
        {
            Long aLong = ((List<Long>) transaction.get(10)).get(0);
            System.out.println(Long.toBinaryString(aLong));
        }
        {
            Long aLong = ((List<Long>) transaction.get(11)).get(0);
            System.out.println(Long.toBinaryString(aLong));
        }
        {
            Long aLong = ((List<Long>) transaction.get(12)).get(0);
            System.out.println(Long.toBinaryString(aLong));
        }
    }

    /**
     * spring boot 1.5.x 中 redis 客户端默认为 Jedis
     * spring boot 2.x 中 redis 客户端默认为 Lettuce
     */
    @Test
    public void redisTest() {
        String key = String.valueOf(System.currentTimeMillis());
//        String key = "abcd";

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        StreamOperations<String, Object, Object> streamOperations = stringRedisTemplate.opsForStream();

        HyperLogLogOperations<String, String> hyperLogLogOperations = stringRedisTemplate.opsForHyperLogLog();

        {
            stringRedisTemplate.multi();
            {

            }
            stringRedisTemplate.exec();
        }

        {
            //SETBIT
            //GETBIT
            //BITFIELD

            //BITCOUNT
            //BITPOS
            //BITOP

            valueOperations.setBit(key, 0, true);
            valueOperations.getBit(key, 0);

            List<Long> longs = valueOperations.bitField(key, BitFieldSubCommands.create()
                    .set(BitFieldSubCommands.BitFieldType.INT_64).valueAt(0).to(15L)
                    .set(BitFieldSubCommands.BitFieldType.INT_32).valueAt(0).to(7L)
                    .set(BitFieldSubCommands.BitFieldType.INT_16).valueAt(0).to(3L)
                    .set(BitFieldSubCommands.BitFieldType.INT_8).valueAt(0).to(1L)
                    .get(BitFieldSubCommands.BitFieldType.INT_64).valueAt(0));
            System.out.println(longs);

            System.out.println();

            for (int i = 0; i < 64; i++) {
                System.out.println(valueOperations.getBit(key, i));
            }

        }

        BoundHashOperations<String, Object, Object> boundHashOperations = stringRedisTemplate.boundHashOps(key);
    }
}
