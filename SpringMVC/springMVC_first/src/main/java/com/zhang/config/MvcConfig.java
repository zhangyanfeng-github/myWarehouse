package com.zhang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@EnableWebMvc   //handlerAdapter配置了json转换器
@Configuration
@ComponentScan("com.zhang")
public class MvcConfig {

    //因为加入了@EnableWebMvc注解，所以不用再添加@Bean这俩个配置
    //@EnableWebMvc底层会自动返回这两个对象

//    @Bean
//    public RequestMappingHandlerMapping handlerMapping() {
//        return new RequestMappingHandlerMapping();
//    }
//
//    @Bean
//    public RequestMappingHandlerAdapter handlerAdapter() {
//        return new RequestMappingHandlerAdapter();
//    }
}
