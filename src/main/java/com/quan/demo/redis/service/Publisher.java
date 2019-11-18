package com.quan.demo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("normal-publisher")
public class Publisher implements MessagePublisher {

    private final StringRedisTemplate stringRedisTemplate;


    @Autowired
    public Publisher(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void publish(String topic, String message) {
        stringRedisTemplate.convertAndSend(topic, message);
    }



}