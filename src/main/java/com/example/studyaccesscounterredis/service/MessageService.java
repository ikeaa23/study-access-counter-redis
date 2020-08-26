package com.example.studyaccesscounterredis.service;

import com.example.studyaccesscounterredis.cache.Message;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MessageService {

    @Cacheable(cacheNames = "myCache")
    public Message build(String message){
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Message(message);
    }
}
