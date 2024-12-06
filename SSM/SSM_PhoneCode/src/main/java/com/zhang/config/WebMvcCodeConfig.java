package com.zhang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 控制层的配置类（Controller和SpringMVC）
 * 1、配置Controller
 * 2、配置全局异常处理器
 * 3、handlerMapping和handlerAdapter
 * 4、静态资源处理
 * 5、Jsp视图解析器前后缀
 * 6、json转化器
 * 7、拦截器
 * 8、其他配置
 */

@Configuration
@ComponentScan("com.zhang.controller")
public class WebMvcCodeConfig implements WebMvcConfigurer {

    //开启静态资源处理器
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }

    //开启视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp("/WEB-INF/views/","jsp");
    }

    //拦截器的配置

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
