package com.example.studyaccesscounterredis.controller;

import com.example.studyaccesscounterredis.service.AccessCounterService;
import com.example.studyaccesscounterredis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessCounterController {

    @Autowired
    AccessCounterService accessCounterService;

    @Autowired
    CacheService cacheService;

    @GetMapping("/")
    public String getAccessCounter(){
        String accessCount = accessCounterService.returnAccessCount();
        String like = cacheService.returnSampleString();
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>ようこそ</title></head>" +
                "<body><h2>Home Page</h2><p>現在のアクセス数：" + accessCount + "</p><br>" +
                "<p>like : " + like + "</p>" +
                "<form action=\"http://localhost:8080/\" method=\"post\"><input type=\"text\" name=\"confirm\" value=\"\" placeholder=\"like\"> <input type=\"submit\" value=\"redisに保存\"></form><br>" +
                "<a href=\"http://localhost:8080/cacheEvict\">キャッシュを削除</a></body></html>";
    }

    @PostMapping("/")
    public String postAccessCounter(String confirm){
        cacheService.saveLike(confirm);
        return getAccessCounter();
    }

    @GetMapping("/cacheEvict")
    public String getAccessCounterCacheEvict(){
        cacheService.removeCache();
        return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head>" +
                "<body><h2></h2><p>キャッシュを削除しました</p>" +
                "<a href=\"http://localhost:8080/\">Homeへ戻る</a></body></html>";
    }
}
