package com.zhang.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring的初始化类
 */
public class SpringIocInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    //root容器的配置类指定
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataSourceEmailConfig.class, MapperEmailConfig.class, ServiceEmailConfig.class};
    }

    //Web容器的配置类指定
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcEmailConfig.class};
    }

    //dispatcherServlet的拦截路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}