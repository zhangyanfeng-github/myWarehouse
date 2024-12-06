package com.zhang.service.impl;

import com.zhang.mapper.CodeMapper;
import com.zhang.pojo.User;
import com.zhang.service.CodeService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeServiceImpl implements CodeService {
    @Resource
    private CodeMapper codeMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    //生成六位验证码
    @Override
    public String sendCode(User user) {
        //调方法，生成6位数的验证码，或者加入服务器，真实发送验证
        String phoneCode = generateCode();

        String key = "user:" + user.getName();

        redisTemplate.opsForHash().put(key, "username", user.getName());
        redisTemplate.opsForHash().put(key, "phone", user.getPhone());
        redisTemplate.opsForHash().put(key, "code", phoneCode);

        if (redisTemplate.opsForHash().get(key, "code") == null) {
            return "发送失败";
        }
        return "发送成功！";
    }
    @Override
    public Integer addUser(String name, String phone, String code) {

        String key = "user:" + name;

        String username = (String) redisTemplate.opsForHash().get(key, "username");
        String userphone = (String) redisTemplate.opsForHash().get(key, "phone");
        String usercode = (String) redisTemplate.opsForHash().get(key, "code");

        System.out.println("name = " + name + ", phone = " + phone + ", code = " + code);
        System.out.println("username = " + username + ", userphone = " + userphone + ", usercode = " + usercode);
        if (name.equals(username) && phone.equals(userphone) && code.equals(usercode)) {
            return codeMapper.insert(name, phone);
        }
        return 0;
    }
    //生成六位验证码的方法
    public static String generateCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomDigit = random.nextInt(10); // 生成0-9的随机数
            sb.append(randomDigit);
        }
        return sb.toString();
    }
}
