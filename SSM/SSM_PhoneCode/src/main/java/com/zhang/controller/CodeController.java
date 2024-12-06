package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class CodeController {

    @Autowired
    private CodeService codeService;

    //发送验证码
    @PostMapping
    public String sendCode(@Validated @RequestBody User user) {
        String result = codeService.sendCode(user);
        System.out.println(result);
        return result;
    }

    /**
     * 添加用户
     * @param name 输入的姓名
     * @param phone 手机号
     * @param code 验证码
     * @return 返回结果
     */
    @GetMapping("/{name}/{phone}/{code}")
    public String addUser(@PathVariable String name,
                          @PathVariable String phone,
                          @PathVariable String code) {

        Integer rows = codeService.addUser(name, phone, code);

        if (rows == 0){
            System.out.println("验证失败，重新输入");
            return "验证失败，重新输入";
        }
        System.out.println("验证通过");
        return "注册成功！";
    }

}
