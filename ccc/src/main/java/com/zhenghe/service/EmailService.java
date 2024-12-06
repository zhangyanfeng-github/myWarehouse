package com.zhenghe.service;

import com.zhenghe.pojo.Email;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendSimpleMessage(String to, String subject, String content);

    void postEmail(Email email);
}