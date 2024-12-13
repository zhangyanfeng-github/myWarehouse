package com.zhang.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.InvalidProtocolBufferException;
import com.zhang.pojo.User;
import com.zhang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author : 张彦锋
 * Date : 2024/12/8  13:49
 * Text :
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void redisInsert(List<CanalEntry.Column> columns) {
        Map<String, Object> map = new HashMap<>();
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
            map.put(column.getName(), column.getValue());
        }
        if (!columns.isEmpty()) {
            String key = "user:" + columns.get(0).getValue();
            try {
                redisTemplate.opsForHash().putAll(key, map);
                System.out.println("执行了redisInsert,key =" + key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void redisDelete(List<CanalEntry.Column> columns) {

        for (CanalEntry.Column column : columns) {
            if (column.getName().equals("uid")) {
                redisTemplate.delete("user:" + column.getValue());
                System.out.println("Redis缓存已删除， uid=" + column.getValue());
            }
        }
    }

    @Override
    public void redisUpdate(List<CanalEntry.Column> columns) {
        Map<String, Object> map = new HashMap<>();
        for (CanalEntry.Column column : columns) {
            if (column.getName().equals("is_deleted") && column.getValue().equals("1")) { // 如果是删除操作，则跳过
                redisDelete(columns);
                return;
            }
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
            map.put(column.getName(), column.getValue());
        }
        if (!columns.isEmpty()) {
            String key = "user:" + columns.get(0).getValue();
            try {
                redisTemplate.opsForHash().putAll(key, map);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User getUserByKey(String key) {
        if (redisTemplate.opsForHash().size(key) > 0) {
            User user = new User();
            Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
            user.setUid(Integer.valueOf(entries.get("uid").toString()));
            user.setUserName(entries.get("user_name").toString());
            user.setUserAge(Integer.valueOf(entries.get("user_age").toString()));
            user.setUserPhone(entries.get("user_phone").toString());
            user.setUserEmail(entries.get("user_email").toString());
            user.setVersion(Integer.valueOf(entries.get("version").toString()));
            user.setIsDeleted(Integer.valueOf(entries.get("is_deleted").toString()));
            return user;
        }
        return null;
    }

    @Override
    public void saveUser(User user) {

        String key = "user:" + user.getUid();
        Map<String, Object> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("user_name", user.getUserName());
        map.put("user_age", user.getUserAge());
        map.put("user_phone", user.getUserPhone());
        map.put("user_email", user.getUserEmail());
        map.put("version", user.getVersion());
        map.put("is_deleted", user.getIsDeleted());
        redisTemplate.opsForHash().putAll(key, map);
    }

}