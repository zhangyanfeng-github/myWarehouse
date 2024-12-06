package com.zhang.spring6.resourceloader;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceLoaderDemo {

    @Test
    public void demo01() {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("D:\\a.txt");
        System.out.println(resource.getFilename());
    }

    @Test
    public void demo02() {
        ApplicationContext context = new FileSystemXmlApplicationContext();
        Resource resource = context.getResource("D:\\a.txt");
        System.out.println(resource.getFilename());
    }
}
