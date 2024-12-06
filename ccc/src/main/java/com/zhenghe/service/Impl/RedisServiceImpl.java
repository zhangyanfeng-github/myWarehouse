package com.zhenghe.service.Impl;

import com.zhenghe.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void setRedisValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getRedisValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}