package com.example.studyaccesscounterredis.service;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {

    String returnSampleString();

    void saveLike(String str);

    void removeCache();
}
