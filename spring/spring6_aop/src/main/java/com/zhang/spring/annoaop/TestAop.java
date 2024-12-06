package com.zhang.spring.annoaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void testAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(3, 4);
    }
}
