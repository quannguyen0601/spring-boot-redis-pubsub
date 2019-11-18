package com.quan.demo.redis.controller;

import com.quan.demo.redis.service.MessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestPublisher {

    @Autowired
    MessagePublisher messagePublisher;

    @GetMapping("/{channel}/{message}")
    public String testPublic(@PathVariable String channel, @PathVariable String message){
        log.info("Channel "+channel +" message: "+message);

        messagePublisher.publish(channel,message);

        return "OK";
    }
}
