package com.zhang.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Author : 张彦锋
 * Date : 2024/12/14  17:58
 * Text :
 */
@Component
@Slf4j
public class BloomFilterInit {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 初始化白名单数据
     */
    @PostConstruct
    public void init() {
        //将白名单用户加入到布隆过滤器中
        String key = "user:2";
        //计算hashValue，因为存在负数，所以取绝对值
        int hashValue = Math.abs(key.hashCode());
        //通过hashValue和2的32次方后取余，获取对应的下标坑位
        long index = (long) (hashValue % Math.pow(2, 32));
        log.info(key + "的下标坑位为：" + index);
        //设置Redis里面的BitMap对应类型的白名单，whitelistUser的坑位，将该值设置为1
        redisTemplate.opsForValue().setBit("whitelistUser", index, true);
    }

}
