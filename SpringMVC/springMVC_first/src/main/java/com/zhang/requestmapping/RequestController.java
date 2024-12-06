package com.zhang.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("request")
public class RequestController {

    //请求方式注解@RequestMapping
    //@GetMapping和PostMapping

    @GetMapping("getRequest")
    public String getRequest() {
        return "@GetMapping";
    }

    //报错，因为浏览器不能发送Post请求
    @PostMapping("postRequest")
    public String postRequest() {
        return "@PostMapping";
    }


    //两种都可以
    @RequestMapping(value = "two", method = {RequestMethod.GET, RequestMethod.POST})
    public String two() {
        return "hello";
    }

}
