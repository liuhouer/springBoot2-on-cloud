package com.imooc.controller;


import com.imooc.dao.StuDao;
import com.imooc.entity.StudentMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate redisTemplate; // 这个类是SpringBoot自己有的

    @GetMapping("create")
    public String create(String k,String v){
        redisTemplate.opsForValue().set(k,v);
        return "ok";
    }

    @GetMapping("get")
    public Object get(String k){
        return redisTemplate.opsForValue().get(k);
    }

    // 更新和插入用的同一个
    @GetMapping("update")
    public String update(String k,String v){
        redisTemplate.opsForValue().set(k,v);
        return "ok";
    }

    @GetMapping("delete")
    public String delete(String k){
        redisTemplate.delete(k);
        return "ok";
    }
}
