package com.zhang.spring6.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

//测试FileSystemResource实现类：访问文件资源系统
public class FileSystemResourceDemo {

    public static void main(String[] args) {
        loadFileSystemResource("D:\\a.txt");
    }

    public static void loadFileSystemResource(String path) {
        //创建对象
        FileSystemResource resource = new FileSystemResource(path);

        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

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
