package com.zhang.service.impl;

import com.zhang.pojo.Email;
import com.zhang.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Author : 张彦锋
 * Date : 2024/11/27  17:25
 * Text :
 */
@Service
@Slf4j
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Object sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());   //设置收件人
        message.setSubject(email.getSubject());     //设置主题
        message.setText(email.getContent());        //设置内容
        message.setFrom(from);     //设置发件人
        try {
            javaMailSender.send(message);
            log.info("发送成功");
            return "发送成功！";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "发送失败";
    }

}
