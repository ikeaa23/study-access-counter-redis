package com.example.studyaccesscounterredis.cache;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;
    private LocalDateTime now;

    public Message(String value){
        this.value = value;
        this.now = LocalDateTime.now();
    }
}
