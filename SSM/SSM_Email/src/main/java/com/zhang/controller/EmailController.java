package com.zhang.controller;

import com.zhang.pojo.Email;
import com.zhang.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public String addEmail(@Validated @RequestBody Email email) {

        emailService.addEmail(email);
        return "ok";
    }

    /**
     * 根据寄件人来查邮件，后端已测试好
     * @param saveName  寄件人邮箱号
     * @return
     */
    @GetMapping("/{saveName}")
    public String getEmailBySaveName(@PathVariable String saveName) {
        String result = emailService.getEmailBySaveName(saveName);
        return result;
    }
}
