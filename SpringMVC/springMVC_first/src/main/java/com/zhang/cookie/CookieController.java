package com.zhang.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("cookie")
public class CookieController {


    //取Cookie值
    @RequestMapping("getCookie")
    public String getCookie(@CookieValue(value = "cookieName") String value) {
        System.out.println("value = " + value);
        return "value = " + value;
    }

    //存Cookie值
    @RequestMapping("setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("cookieName", "root");
        response.addCookie(cookie);
        return "ok";
    }
}
