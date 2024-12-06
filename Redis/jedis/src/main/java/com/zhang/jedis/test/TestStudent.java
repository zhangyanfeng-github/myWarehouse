package com.zhang.jedis.test;

import com.zhang.jedis.config.RedisConfig;
import com.zhang.jedis.pojo.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class TestStudent {

    private Jedis jedis;

    @Before
    public void createJedis() {
        jedis = new Jedis("192.168.10.102", 6379);
        jedis.auth("123456");
    }

    @After
    public void closeJedis() {
        jedis.close();
    }


    //测试Hash，传入对象值
    @Test
    public void testStudent() {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(RedisConfig.class);
        Student student = context.getBean(Student.class);

        student.setId("10001");
        student.setName("张彦锋");
        student.setAge(23);
        System.out.println(student);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", student.getId());
        hashMap.put("name", student.getName());
        hashMap.put("age", new String().valueOf(student.getAge()));

        jedis.hset("student", hashMap);
    }

}

