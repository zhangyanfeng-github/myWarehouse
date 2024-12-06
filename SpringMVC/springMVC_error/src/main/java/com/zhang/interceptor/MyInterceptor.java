package com.zhang.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//拦截器，配置完拦截器后，需要注入到Config配置类中（MvcConfig）
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 在执行handler之前，调用的方法
     * 比如设置编码格式，登录保护。权限处理等拦截
     * @param request   请求对象
     * @param response  相应对象
     * @param handler   就是我们要调用的方法对象
     * @return  返回true代表放行，返回false代表拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request = " + request + ", response = " + response + ", handler = " + handler);
        return true;
    }

    /**
     * 当handler执行完毕后，触发的方法，此时已经没有拦截机制了，因为返回true才会执行此方法
     * 例如对结果进行处理，对铭感词汇进行检查
     * @param request   请求对象
     * @param response  相应对象
     * @param handler   handler方法
     * @param modelAndView  返回的视图和共享域数据对象
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor.postHandle");
    }

    /**
     * 当整体处理完毕后执行此方法
     * @param request   请求对象
     * @param response  相应对象
     * @param handler   handler方法
     * @param ex    异常对象
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor.afterCompletion");
    }
}

