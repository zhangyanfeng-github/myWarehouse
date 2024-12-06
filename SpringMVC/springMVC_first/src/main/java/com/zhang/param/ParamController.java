package com.zhang.param;

import com.zhang.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody   //直接返回字符串给前端，不会去找视图解析器
@RequestMapping("param")
public class ParamController {

    //直接接收方式
    //http://localhost:8080/param/data1?name=zhang&age=10
    @RequestMapping("data1")
    public String data1(String name, int age) {
        System.out.println("name = " + name + ", age = " + age);
        return "name = " + name + ", age = " + age;
    }

    //注解接收方式
    //可以通过value来起个别名，浏览器只需要传入account，会自动被username接收
    //required = false表示可以不传入，defaultValue = "18"表示默认值18
    //http://localhost:8080/param/data2?account=zhang，不传入age，会默认为18
    @RequestMapping("data2")
    public String data2(@RequestParam(value = "account") String username,
                        @RequestParam(required = false, defaultValue = "18") int age) {
        System.out.println("username = " + username + ", age = " + age);
        return "username = " + username + ", age = " + age;
    }

    //特殊值的接收（一键多值，例如爱好）
    //http://localhost:8080/param/data3?loves=play&loves=eat
    @RequestMapping("data3")
    public String data3(@RequestParam List<String> loves) {
        System.out.println("loves = " + loves);
        return "loves = " + loves;
    }

    //特殊值的接收（使用实体类来接收对象，需要提前创建好实体类）
    //http://localhost:8080/param/data4?name=zhangsan&age=3
    //http://localhost:8080/param/data4?name=lisi，当实体类中定义了年龄，也就是默认值，不传也会有
    @RequestMapping("data4")
    public String dada4(User user) {
        System.out.println("user = " + user);
        return "user = " + user;
    }
}
