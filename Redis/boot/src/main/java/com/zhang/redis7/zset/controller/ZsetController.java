package com.zhang.redis7.zset.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("zset")
@Api(tags = "测试zset类型")
public class ZsetController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("add")
    @ApiOperation("向集合中插入元素，并设置分数score")
    public String add() {
        redisTemplate.opsForZSet().add("zset", "22", 12);
        redisTemplate.opsForZSet().add("zset", "11", 13);
        redisTemplate.opsForZSet().add("zset", "33", 11);
        return "ok";
    }

    @GetMapping("range")
    @ApiOperation("从小到大输出元素")
    public Set<Object> range() {
        return redisTemplate.opsForZSet().range("zset", 0, -1);
    }

    @GetMapping("score")
    @ApiOperation("获得指定元素的分数")
    public Double score() {
        return redisTemplate.opsForZSet().score("zset", "11");
    }

    @GetMapping("size")
    @ApiOperation("获得集合中元素个数")
    public Long size() {
        return redisTemplate.opsForZSet().size("zset");
    }

    @GetMapping("count")
    @ApiOperation("返回集合内指定分数范围内的成员个数（double类型）")
    public Long count() {
        return redisTemplate.opsForZSet().count("zset", 0, 20);
    }

    @GetMapping("rangeByScore")
    @ApiOperation("返回集合内元素在指定分数范围内的排名（小到大）")
    public Set<Object> rangeByScore() {
        return redisTemplate.opsForZSet().rangeByScore("zset", 0, 20);
    }

    @GetMapping("rank")
    @ApiOperation("返回指定成员的排名")
    public Long rank() {
        return redisTemplate.opsForZSet().rank("zset", "11");
    }

    @GetMapping("remove")
    @ApiOperation("删除某个元素")
    public Long remove() {
        return redisTemplate.opsForZSet().remove("zset", "11");
    }

    @GetMapping("removeRange")
    @ApiOperation("删除指定索引范围内的元素")
    public Long removeRange() {
        return redisTemplate.opsForZSet().removeRange("zset", 0, 1);
    }

    @GetMapping("removeRangeByScorssse")
    @ApiOperation("删除指定分数范围内的元素")
    public Long removeRangeByScorssse() {
        return redisTemplate.opsForZSet().removeRangeByScore("zset", 10, 20);
    }

    @GetMapping("incrementScore")
    @ApiOperation("为指定元素加分（double类型）")
    public Double incrementScore() {
        return redisTemplate.opsForZSet().incrementScore("zset", "22", 20);
    }


}
