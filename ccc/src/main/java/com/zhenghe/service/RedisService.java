package com.zhenghe.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {
    void setRedisValue(String key, String value);
    String getRedisValue(String key);
}
