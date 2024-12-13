package com.zhang.service;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhang.pojo.User;

import java.util.List;

/**
 * Author : 张彦锋
 * Date : 2024/12/8  13:48
 * Text :
 */
public interface RedisService {

    void redisInsert(List<CanalEntry.Column> afterColumnsList);

    void redisDelete(List<CanalEntry.Column> beforeColumnsList);

    void redisUpdate(List<CanalEntry.Column> afterColumnsList);

    User getUserByKey(String key);

    void saveUser(User user);
}
