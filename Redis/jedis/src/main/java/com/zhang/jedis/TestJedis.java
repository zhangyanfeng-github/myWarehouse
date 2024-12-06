package com.zhang.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Set;

public class TestJedis {

    private Jedis jedis;

    @Before
    public void createJedis() {
        jedis = new Jedis("192.168.10.104", 6381);
        jedis.auth("123456");
    }

    @Test
    public void testRedis() {
        int code = new Random().nextInt(9000) + 1000;
        String string = Integer.toString(code);
        jedis.setex("code", 60, string);
    }

    @After
    public void closeJedis() {
        jedis.close();
    }

    @Test
    public void testSet() {
        jedis.sadd("name", "zhangsan", "lisi", "wangwu");
        Set<String> names = jedis.smembers("name");
        for (String name : names) {
            System.out.println(name);
        }

        jedis.sadd("age", "23", "19", "44", "33", "18");
        Set<String> ages = jedis.smembers("name");
        for (String age : ages) {
            System.out.println(age);
        }

        System.out.println(jedis.sismember("name", "zhangsan"));
        System.out.println(jedis.scard("name"));
        System.out.println(jedis.srem("name", "zhangsan"));
        System.out.println(jedis.spop("age"));
        System.out.println(jedis.srandmember("age", 2));
        jedis.smove("name", "age", "wangwu");
        System.out.println(jedis.sinter("name", "age"));
        System.out.println(jedis.sunion("name", "age"));
        System.out.println(jedis.sdiff("name", "age"));

    }

    @Test
    public void testCluster(){
        System.out.println(jedis.get("k1"));
    }

}
