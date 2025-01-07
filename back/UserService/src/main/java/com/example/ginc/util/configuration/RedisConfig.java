package com.example.ginc.util.configuration;

import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    // <key, value> 가 <Long, Stirng> 타입인 데이터를 다룰 때 필요한 RedisTemplate 생성 -> 이메일 인증에 사용
    @Bean
    public RedisTemplate<Long, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Long, String> template = new RedisTemplate<>();
        // 앞에서 정의한 RedisConnectionFactory를 통해 Redis와 연결
        template.setConnectionFactory(redisConnectionFactory);

        // Key와 Value를 String으로 직렬화(serialize)
        template.setKeySerializer(new GenericToStringSerializer<>(Long.class));
        template.setValueSerializer(new GenericToStringSerializer<>(String.class));

        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
}
