package com.zhang.redis7.springboot.controller;


import com.zhang.redis7.springboot.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


//控制类
@RestController
@Api(tags = "用户信息")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation("新增键值")
    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public String setValue(@RequestParam String key, @RequestParam Integer value) {
        redisService.setValue(key, value);
        return "Value：(" + key + ") set";
    }

    @ApiOperation("测试方法")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String print() {
        return "welcome to china";
    }


    @ApiOperation("查询")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getValue(@RequestParam String key) {
        Object result = redisService.getValue(key);
        return "Value：(" + result + ") ";
    }

    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteValue(@RequestParam String key) {
        redisService.deleteValue(key);
        return "Value：(" + key + ") deleted";
    }

    private String goods_key = "goods";
    private String USER_ID = "user:";

    @ApiOperation("商品秒杀")
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyGoods() {
        String user_key = USER_ID + new Random().nextInt(20);
        Object result = redisService.buyGoods(goods_key, user_key);
        System.out.println(result);
    }


}
