package com.zhang.config;

import com.zhang.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc   //handlerAdapter配置了json转换器，如果此注解在SpringBoot项目横纵代表了禁用了Mvc的默认功能
@Configuration
@ComponentScan("com.zhang")
public class MvcConfig implements WebMvcConfigurer {

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


    //在MvcConfig配置文件中注入拦截器对象
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置方案1、：拦截全部请求
        //registry.addInterceptor(new MyInterceptor());

        //配置方案2、：指定地址拦截
        //* :任意一层字符串，**：任意多层字符串
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/user/**");

        //配置方案3、排除拦截 排除的地址应该在拦截地址内部；包含的关系
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/data1");
    }
}
