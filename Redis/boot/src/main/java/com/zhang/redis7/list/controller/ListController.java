package com.zhang.redis7.list.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     //不经过视图解析器
@Api(tags = "测试List类型")
@RequestMapping("list")
public class ListController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("push")
    @ApiOperation("向List中添加值")
    public String push() {
        redisTemplate.opsForList().leftPush("mylist", "ele1");
        redisTemplate.opsForList().leftPush("mylist", "ele2");
        redisTemplate.opsForList().leftPush("mylist", "ele3");
        redisTemplate.opsForList().rightPush("mylist", "b");
        redisTemplate.opsForList().rightPush("mylist", "1");
        return "successful";
    }

    @GetMapping("range")
    @ApiOperation("获取List中的值")
    public String range() {
        List<Object> list = redisTemplate.opsForList().range("mylist", 0, -1);
        System.out.println(list);
        return "mylist: " + list;
    }

    @GetMapping("size")
    @ApiOperation("获取List的长度")
    public Long size() {
        Long mylist = redisTemplate.opsForList().size("mylist");
        System.out.println(mylist);
        return mylist;
    }

    @GetMapping("remove")
    @ApiOperation("删除List中的元素")
    public Long remove() {
        Long remove_size = redisTemplate.opsForList().remove("mylist", 0, "ele1");
        System.out.println(remove_size);
        return remove_size;
    }
}
