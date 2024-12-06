package com.zhang.spring6.resource;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

//测试ClassPathResource访问类路径下的资源
public class ClassPathResourceDemo {

    public static void main(String[] args) {
        loadClassPathResource("a.txt");     //a.txt在Class类路径下
    }

    public static void loadClassPathResource(String path) {
        //创建对象ClassPathResource
        ClassPathResource resource = new ClassPathResource(path);

        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

        //获取文件内容
        try {
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes) != -1) {
                System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
