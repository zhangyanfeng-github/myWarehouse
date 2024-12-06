package com.zhang.spring.annoaop.xmlaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void testAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("BeanAop.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(5, 4);
    }
}
