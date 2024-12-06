package com.zhang.header;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("header")
@ResponseBody
@Controller
public class HeaderController {

    //获取请求头
    @RequestMapping("getHeader")
    public String getHeader(@RequestHeader("Host") String host) {
        System.out.println("host = " + host);
        return "host = " + host;
    }

    //获取Accept
    @RequestMapping("getAccept")
    public String getAccept(@RequestHeader("Accept") String host) {
        System.out.println("accept = " + host);
        return "accept = " + host;
    }

    //想要获取浏览器的哪个信息就在@RequestHeader("Accept")，中传入哪个值
}
