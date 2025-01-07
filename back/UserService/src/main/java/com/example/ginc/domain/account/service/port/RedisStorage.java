package com.example.ginc.domain.account.service.port;

public interface RedisStorage {

    String getData(Long key); // 키 타입을 Long으로 수정
    void setData(Long key, String value); // 키 타입을 Long으로 수정
    void setDataExpire(Long key, String value, long duration);
    void deleteData(Long key); // 키 타입을 Long으로 수정
}
