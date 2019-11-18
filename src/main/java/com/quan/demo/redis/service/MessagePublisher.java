package com.quan.demo.redis.service;



public interface MessagePublisher {
    void publish(String topic, String message);
}