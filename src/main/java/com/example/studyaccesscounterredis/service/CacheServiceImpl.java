package com.example.studyaccesscounterredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "cache")
public class CacheServiceImpl implements CacheService{


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Cacheable
    public String returnSampleString(){
        heavyTask();
        String str = redisTemplate.opsForValue().get("like");
        return str;
    }

    @Override
    public void saveLike(String str){
        redisTemplate.opsForValue().set("like", str);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void removeCache(){
    }


    // 重たい処理を表現するためのダミーメソッド
    private void heavyTask() {
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
