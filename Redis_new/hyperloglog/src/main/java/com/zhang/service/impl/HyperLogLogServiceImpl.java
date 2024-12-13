package com.zhang.service.impl;

import com.zhang.service.HyperLogLogService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Author : 张彦锋
 * Date : 2024/12/13  15:35
 * Text :
 */
@Service
@Slf4j
public class HyperLogLogServiceImpl implements HyperLogLogService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        new Thread(() -> {
            String ip = null;
            for (int i = 0; i < 1000; i++) {
                Random random = new Random();
                ip = random.nextInt(256) + "." +
                        random.nextInt(256) + "." +
                        random.nextInt(256) + "." +
                        random.nextInt(256);
                redisTemplate.opsForHyperLogLog().add("hll:uv", ip);
                log.info("ip:{}，该ip地址访问了首页", ip);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "uv").start();
    }

    @Override
    public Long uv() {
        return redisTemplate.opsForHyperLogLog().size("hll:uv");
    }
}
