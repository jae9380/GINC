package com.example.ginc.domain.account.infrastructure;

import com.example.ginc.domain.account.service.port.RedisStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisStorageUtil implements RedisStorage {
    private final RedisTemplate<Long, String> redisTemplate;

    @Override
    public String getData(Long key) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void setData(Long key, String value) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    @Override
    public void setDataExpire(Long key, String value, long duration) {
        ValueOperations<Long, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, duration, TimeUnit.MINUTES);
    }

    @Override
    public void deleteData(Long key) {
        redisTemplate.delete(key);
    }
}
