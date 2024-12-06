package com.zhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhang.mapper") //Mapper接口所在的位置
public class ApplicationMyUserManage {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMyUserManage.class, args);
    }
}