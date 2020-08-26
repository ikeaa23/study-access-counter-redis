package com.example.studyaccesscounterredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccessCounterServiceImpl implements AccessCounterService{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String returnAccessCount() {
        Integer accessCount = Integer.valueOf(redisTemplate.opsForValue().get("access_count")) + 1;
        redisTemplate.opsForValue().set("access_count", Integer.toString(accessCount));
        return Integer.toString(accessCount);
    }
}
