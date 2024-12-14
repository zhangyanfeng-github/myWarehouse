package com.zhang.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Author : 张彦锋
 * Date : 2024/12/14  18:04
 * Text :
 */
@Component
@Slf4j
public class CheckUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean checkBloomFilter(String checkItem, String key) {
        int hashValue = Math.abs(key.hashCode());
        long index = (long) (hashValue % Math.pow(2, 32));
        boolean existOK = redisTemplate.opsForValue().getBit(checkItem, index);
        log.info("--->key:" + key + "对应坑位下标index：" + index + "，是否已存在：" + existOK);
        return existOK;
    }
}
