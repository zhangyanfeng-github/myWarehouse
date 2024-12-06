package com.zhang.redis7.string.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("string")
@Api(tags = "测试String类型")
public class StringController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "setString", method = RequestMethod.GET)
    @ApiOperation("setString")
    public String setString(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        System.out.println("name = " + key + ", value = " + value);
        return "name = " + key + ", value = " + value;
    }

    @RequestMapping(value = "setInt", method = RequestMethod.GET)
    @ApiOperation("setInt")
    public String setInt(@RequestParam String key, @RequestParam int value) {
        redisTemplate.opsForValue().set(key, value);
        System.out.println("name = " + key + ", value = " + value);
        return "name = " + key + ", value = " + value;
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ApiOperation("get")
    public String get(@RequestParam String key) {
        Object result = redisTemplate.opsForValue().get(key);
        System.out.println("result = " + result);
        return "result = " + result;
    }

}
