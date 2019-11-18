package com.quan.demo.redis.configuration;

import com.quan.demo.redis.service.Subscriber;
import com.quan.demo.redis.service.SubscriberSecond;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    private static final String CHANNEL_NAME = "ch1";

    private static final String CHANNEL_NAME2 = "ch2";

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new Subscriber());
    }

    @Bean
    public MessageListenerAdapter messageListener2() {
        return new MessageListenerAdapter(new SubscriberSecond());
    }

    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();

        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        container.addMessageListener(messageListener2(), topic2());

        return container;
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic(CHANNEL_NAME);
    }

    @Bean
    public ChannelTopic topic2() {
        return new ChannelTopic(CHANNEL_NAME2);
    }
}