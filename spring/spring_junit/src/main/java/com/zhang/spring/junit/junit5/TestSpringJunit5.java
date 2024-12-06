package com.zhang.spring.junit.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@SpringJUnitConfig(locations = "classpath:bean.xml")

//第二种方式，用的不多，了解
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean.xml")

public class TestSpringJunit5 {

    @Autowired
    private User user;

    //测试方法
    @Test
    public void testUser() {

        //Spring整合Junit过程
        //因为有注解，所以不需要再次创建Spring容器对象，可以省略一下俩个步骤
//        ApplicationContext context = new
//                ClassPathXmlApplicationContext("bean.xml");
//        User user = context.getBean(User.class);

        System.out.println(user);
        user.run();
    }
}
