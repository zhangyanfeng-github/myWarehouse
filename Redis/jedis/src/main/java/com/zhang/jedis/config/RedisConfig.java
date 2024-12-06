package com.zhang.jedis.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration      //定义配置类
@ComponentScan("com.zhang.jedis")   //开启组件扫描
public class RedisConfig {
}

