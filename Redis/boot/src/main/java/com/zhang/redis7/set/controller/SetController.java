package com.zhang.redis7.set.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Api(tags = "测试Set类型")
@RestController
@RequestMapping("set")
public class SetController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("add")
    @ApiOperation("向Set添加值")
    public String add() {
        redisTemplate.opsForSet().add("set", "22", "33");
        redisTemplate.opsForSet().add("set", "11");
        redisTemplate.opsForSet().add("set", "55");
        return "ok";
    }

    @GetMapping("remove")
    @ApiOperation("移除指定元素")
    public Long remove() {
        return redisTemplate.opsForSet().remove("set", "11");
    }

    @GetMapping("size")
    @ApiOperation("计算Set大小")
    public Long size() {
        return redisTemplate.opsForSet().size("set");
    }

    @GetMapping("isMember")
    @ApiOperation("判断集合中是否存在某元素")
    public Boolean isMember() {
        return redisTemplate.opsForSet().isMember("set", "88");
    }

    @GetMapping("randomMember")
    @ApiOperation("随机获取集合中一个元素")
    public String randomMember() {
        return (String) redisTemplate.opsForSet().randomMember("set");
    }

    @GetMapping("randomMembers")
    @ApiOperation("随机获取集合中指定个数的元素，可重复")
    public List<Object> randomMembers() {
        return redisTemplate.opsForSet().randomMembers("set", 2);
    }

    @GetMapping("distincRandomMembers")
    @ApiOperation("随机获取集合中指定个数的元素，不重复")
    public Set<Object> distincRandomMembers() {
        return redisTemplate.opsForSet().distinctRandomMembers("set", 2);
    }

    @GetMapping("pop")
    @ApiOperation("随机移除一个元素")
    public Object pop() {
        return redisTemplate.opsForSet().pop("set");
    }

    @GetMapping("members")
    @ApiOperation("获取集合内的所有元素")
    public Set<Object> members() {
        return redisTemplate.opsForSet().members("set");
    }

    @GetMapping("move")
    @ApiOperation("向另一个集合中移动一个元素")
    public Boolean move() {
        return redisTemplate.opsForSet().move("set", "22", "set1");
    }

    @GetMapping("intersect")
    @ApiOperation("取交集")
    public Set<Object> intersect() {
        return redisTemplate.opsForSet().intersect("set", "set1");
    }

    @GetMapping("union")
    @ApiOperation("取并集")
    public Set<Object> union() {
        return redisTemplate.opsForSet().union("set", "set1");
    }

}
