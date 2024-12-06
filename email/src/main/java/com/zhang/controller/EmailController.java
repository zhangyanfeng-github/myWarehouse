package com.zhang.controller;

import com.zhang.pojo.Email;
import com.zhang.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : 张彦锋
 * Date : 2024/11/27  17:28
 * Text :
 */
@RestController
@RequestMapping("email")
@CrossOrigin
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * Send email object.
     *
     * @param email the email
     * @return the object
     */
    @PostMapping
    public Object sendEmail(@RequestBody Email email) {
        log.info("发送邮件：{}", email);
        return emailService.sendEmail(email);

    }
}
