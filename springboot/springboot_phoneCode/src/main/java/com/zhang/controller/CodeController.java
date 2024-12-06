package com.zhang.controller;

import com.zhang.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class CodeController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping
    public String sendCode(@Validated @RequestBody User user) {

        String key = "user:" + user.getName();

        //调方法，生成6位数的验证码，或者加入服务器，真实发送验证
        String phoneCode = generateCode();

        redisTemplate.opsForHash().put(key, "userName", user.getName());
        redisTemplate.opsForHash().put(key, "phoneNumber", user.getPhone());
        redisTemplate.opsForHash().put(key, "phoneCode", phoneCode);
        Object name = redisTemplate.opsForHash().get(key, "userName");
        Object phone = redisTemplate.opsForHash().get(key, "phoneNumber");
        Object code = redisTemplate.opsForHash().get(key, "phoneCode");

        System.out.println("name = " + name + ", phone = " + phone + ", code = " + code);
        return "ok";
    }

    @GetMapping("{userName}/{phoneCode}")
    public ResponseEntity<String> checkCode(@PathVariable String userName,
                                    @PathVariable String phoneCode) {
        String key = "user:" + userName;
        if (phoneCode.equals(redisTemplate.opsForHash().get(key, "phoneCode"))) {
            return ResponseEntity.ok("验证成功");
        }
        return ResponseEntity.ok("验证码错误");
    }

    //生成六位数的验证码
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
