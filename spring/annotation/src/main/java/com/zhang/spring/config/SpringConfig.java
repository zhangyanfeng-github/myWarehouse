package com.zhang.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  //定义配置类
@ComponentScan("com.zhang.spring.resource")      //开启组件扫描
public class SpringConfig {
}
