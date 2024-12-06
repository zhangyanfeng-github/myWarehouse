package com.zhang.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.utils.JwtHelper;
import com.zhang.utils.Result;
import com.zhang.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.Reader;

/**
 * 登录保护拦截器，检查请求头中是否包含有效的token
 * <br/>
 * 若有效就放行，无效返回504错误
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;


    /**
     * 配置拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");

        //检查token是否有效
        boolean expiration = jwtHelper.isExpiration(token);

        //有效放行
        if (!expiration) {
            return true;
        }

        //无效拦截
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);

        ObjectMapper objectMapper = new ObjectMapper();
        //将result结果转为json字符串
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);

        return false;
    }
}
