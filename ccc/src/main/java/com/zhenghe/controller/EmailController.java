package com.zhenghe.controller;

import com.zhenghe.pojo.Email;
//import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Consumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhenghe.service.EmailService;

@ResponseBody
@Controller
@RequestMapping("email")
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

//    @GetMapping("send/{to}/{subject}/{content}")
//    public String sendEmail(@PathVariable String to, @PathVariable String subject, @PathVariable String content) {
//
//        emailService.sendSimpleMessage(to, subject, content);
//
//        return "redirect:/";
//    }

    @PostMapping("send")
    @Consumes("application/x-www-form-urlencoded")
    public String sendEmail(@RequestBody Email email) {
        System.out.println("email = " + email);
        emailService.postEmail(email);

        return "redirect:/";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}