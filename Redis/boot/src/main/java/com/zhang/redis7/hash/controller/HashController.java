package com.zhang.redis7.hash.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api(tags = "测试Hash类型")
@RequestMapping("hash")
public class HashController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("put")
    @ApiOperation("添加数据")
    public String put() {
        redisTemplate.opsForHash().put("hash:001", "name", "zhangsan");
        redisTemplate.opsForHash().put("hash:001", "age", "21");
        redisTemplate.opsForHash().put("hash:001", "sex", "man");
        return "successful";
    }

    @GetMapping("get")
    @ApiOperation("获取数据")
    public Object get() {
        Object name = redisTemplate.opsForHash().get("hash:001", "name");
        System.out.println(name);
        return name;
    }

    @GetMapping("entries")
    @ApiOperation("获取所有的键值对")
    public Map<Object, Object> entries() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("hash:001");
        System.out.println(entries);
        return entries;
    }

    @GetMapping("keys")
    @ApiOperation("获取所有的keys")
    public Set<Object> keys() {
        Set<Object> keys = redisTemplate.opsForHash().keys("hash:001");
        System.out.println(keys);
        return keys;
    }

    @GetMapping("values")
    @ApiOperation("获取所有的values")
    public List<Object> values() {
        List<Object> values = redisTemplate.opsForHash().values("hash:001");
        System.out.println(values);
        return values;
    }

}
