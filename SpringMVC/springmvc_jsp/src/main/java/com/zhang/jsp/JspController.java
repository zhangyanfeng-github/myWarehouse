package com.zhang.jsp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("jsp")
public class JspController {

    //不能添加@ResponseBody，直接返回字符串给浏览器
    //返回值对应中间的视图名称即可
    @GetMapping("index")
    public String index(HttpServletRequest request) {
        request.setAttribute("data", "hello jsp!");
        System.out.println("测试");
        return "index";
    }
}
