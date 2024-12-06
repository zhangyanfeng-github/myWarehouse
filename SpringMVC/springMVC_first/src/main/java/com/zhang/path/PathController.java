package com.zhang.path;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("path")
public class PathController {

    //根据路径参数来接收值
    //http://localhost:8080/path/{admin}/{12345}
    //直接在{}里边传入值，会自动读取

    @RequestMapping("{account}/{password}")
    public String login(@PathVariable(value = "account") String username,
                        @PathVariable String password) {
        System.out.println("username = " + username + ", password = " + password);
        return "username = " + username + ", password = " + password;
    }
}
