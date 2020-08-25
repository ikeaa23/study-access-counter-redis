package com.example.studyaccesscounterredis.controller;

import com.example.studyaccesscounterredis.service.AccessCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessCounterController {

    @Autowired
    AccessCounterService accessCounterService;

    @GetMapping("/")
    public String getAccessCounter(){
        String accessCount = accessCounterService.returnAccessCount();
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>ようこそ</title></head><body>現在のアクセス数：" + accessCount + "</body></html>";
    }
}
