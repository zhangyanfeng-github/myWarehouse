package com.zhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhang.mapper") //Mapper接口所在的位置
@SpringBootApplication
public class ApplicationStudent {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStudent.class, args);
    }
}