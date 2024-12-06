package com.zhang.spring6.resource;

import org.springframework.core.io.UrlResource;

//测试UrlResources实现类，访问网络资源
public class UrlResourcesDemo {

    public static void main(String[] args) {
        //http前缀
        loadUrlResource("http://www.baidu.com");
        //File前缀
        loadUrlResource("file:D:/a.txt");
    }

    //访问前缀http或file
    public static void loadUrlResource(String path){
        try {
            //创建Resource实现类的对象UrlResource
            UrlResource url = new UrlResource(path);

            //获取资源信息
            System.out.println(url.getFilename());
            System.out.println(url.getURL());
            System.out.println(url.getDescription());   //输出描述
            System.out.println(url.getInputStream().read());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
