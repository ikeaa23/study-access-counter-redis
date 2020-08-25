package com.example.studyaccesscounterredis.service;

import org.springframework.stereotype.Service;

@Service
public interface AccessCounterService {

    String returnAccessCount();

}
