package com.zhang.service.impl;

import com.zhang.mapper.EmailMapper;
import com.zhang.pojo.Email;
import com.zhang.service.EmailService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Integer addEmail(Email email) {
        String key = "email:" + email.getSaveName();
        redisTemplate.opsForHash().put(key, "saveName", email.getSaveName());
        redisTemplate.opsForHash().put(key, "title", email.getTitle());
        redisTemplate.opsForHash().put(key, "content", email.getContent());

        return emailMapper.addEmail(email);
    }

    @Override
    public String getEmailBySaveName(String saveName) {

        String key = "email:" + saveName;

        if (redisTemplate.opsForHash().get(key,"content") == null) {

            Email email_content = (Email) emailMapper.getEmailBySaveName(saveName);
            if (email_content == null) {
                return "此用户没有发送过邮件";
            } else {
                redisTemplate.opsForHash().put(key, "saveName", email_content.getSaveName());
                redisTemplate.opsForHash().put(key, "title", email_content.getTitle());
                redisTemplate.opsForHash().put(key, "content", email_content.getContent());

                return email_content.getContent();
            }
        } else {
            return (String) redisTemplate.opsForHash().get(key, "content");
        }


    }
}
