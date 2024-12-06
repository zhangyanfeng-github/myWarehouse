package com.zhang.redis7.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


//Redis操作类

@Service
public class RedisService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }


    public Object buyGoods(String goods_key, String user_key) {

        if ((Integer) redisTemplate.opsForValue().get(goods_key) < 1) {
            return "暂无库存";
        }

        redisTemplate.opsForValue().set(user_key, 0);
        // 10、商品数量 -1
        redisTemplate.opsForValue().decrement(goods_key);
        //11、user_key +1
        redisTemplate.opsForValue().increment(user_key);

        return "购买成功！";

    }
}
