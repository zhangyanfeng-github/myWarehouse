package com.zhang.test;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author : 张彦锋
 * Date : 2024/12/7  14:52
 * Text :
 */
@SpringBootTest
public class TestDelete {

    /**
     * 测试渐进式删除各种数据类型中的数据
     */

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 成功
     */
    @Test
    public void testDeleteString() {
        System.out.println(redisTemplate.unlink("hello"));
    }

    /**
     * 错误
     */
    @Test
    public void testDeleteHash() {
        ScanOptions scanOptions = ScanOptions.scanOptions().match("user:001").count(100).build();
        Cursor<Map.Entry<Object, Object>> cursor =
                redisTemplate.opsForHash().scan("user:001", scanOptions);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(redisTemplate.opsForHash().delete("user:001", entry.getKey()));
        }
        cursor.close();
    }

    /**
     * 错误
     */
    @Test
    public void delList() {
        Long size = redisTemplate.opsForList().size("user");
        Long counter = 0L;
        int trimNum = 2;  // 每次删除的个数
        while (counter < size) {
            redisTemplate.opsForList().trim("user", counter, counter + trimNum - 1);
            counter += trimNum;
        }
        // 最后检查并删除key，确保List被完全删除
        if (redisTemplate.opsForList().size("user") == 0) {
            System.out.println("if ：" + redisTemplate.delete("user"));
        }
    }

    @Test
    public void delSet() {
        Long size = redisTemplate.opsForSet().size("user");
        Set<Object> membersToRemove = new HashSet<>();
        Long counter = 0L;
        int batchSize = 100;  // 设置每次删除的批次大小

        while (counter < size) {
            // 获取Set中的元素
            Set<Object> members = redisTemplate.opsForSet().members("user");
            membersToRemove.addAll(members);
            if (membersToRemove.size() >= batchSize || members.isEmpty()) {
                // 删除指定的元素
                redisTemplate.opsForSet().remove("user", membersToRemove.toArray());
                membersToRemove.clear();
            }
            counter += batchSize;
        }
    }

    @Test
    public void addSet() {
        redisTemplate.opsForSet().add("user", "001", "002", "003", "004", "005", "006", "007", "008", "009", "010");
    }

    @Test
    public void addHash() {
        redisTemplate.opsForHash().put("user:001", "name", "zhangsan");
        redisTemplate.opsForHash().put("user:001", "age", "18");
        redisTemplate.opsForHash().put("user:001", "sex", "man");

    }
}
