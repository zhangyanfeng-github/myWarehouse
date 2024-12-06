package com.zhang.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("springmvc/hello") //提供对外访问的地址,将handler注册到handlerMapping
    @ResponseBody   //直接返回字符串给前端，不会去找视图解析器
    public String hello() {
        System.out.println("helloController.hello");
        //返回给前端
        return "hello springMVC";
    }

}
