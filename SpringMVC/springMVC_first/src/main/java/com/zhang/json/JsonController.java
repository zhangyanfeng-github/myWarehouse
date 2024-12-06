package com.zhang.json;

import com.zhang.pojo.Person;
import com.zhang.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("json")
@RestController
public class JsonController {

    //这个是接收浏览器给的json数据
    //提前定义Person类，并定义属性
    //因为浏览器无法发送Post请求，以及无法发送json数据，所以需要用到Postman软件来模拟浏览器的请求
    //若报错415，原因：
    //Java原生的API只支持路径参数和Param参数，不支持json
    //解决方法：1、导入json处理的依赖；2、handlerAdopter配置json转化器（在MvcConfig类中配置）

    @RequestMapping("jsondata")
    public String data(@RequestBody Person person) {
        System.out.println("person = " + person);
        return "person = " + person;
    }


    //这两个是相应浏览器响应后，给浏览器返回的json数据
    //前后端分离模式下，给前端返回的数据都是json数据
    @GetMapping("data1")
    public User data1() {
        User user = new User();
        user.setAge(22);
        user.setName("zhangsan");
        return user;
    }

    //也可以返回集合
    @GetMapping("data2")
    public List<User> data2() {
        User user = new User();
        user.setAge(11);
        user.setName("lisi");
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}
