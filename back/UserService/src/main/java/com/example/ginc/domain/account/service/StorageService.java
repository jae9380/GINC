package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.exception.EmailException;
import com.example.ginc.domain.account.service.port.RedisStorage;
import com.example.ginc.util.exception.GincException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StorageService {
    private static final long EXPIRATION_AT_10MINUTES = 10; // 30분
    private final RedisStorage storage;
    @Transactional
    public void StoreAuthCode(Long user_id, String authCode) {

        if (storage.getData(user_id) != null) {
            storage.deleteData(user_id);
        }

        storage.setDataExpire(user_id, authCode, EXPIRATION_AT_10MINUTES);

        log.info("AuthCode Storage Completed \n Data : {"+user_id+", "+authCode+"}");
    }

    @Transactional
    public void deleteAuthCode(Long user_id) {
        storage.deleteData(user_id);
    }

    public String getAuthCode(Long user_id) {

        String storedAuthCode = storage.getData(user_id);

        if (Objects.isNull(storedAuthCode)) {
            throw new EmailException.InitiateEmailRequestException();
        }

        return storedAuthCode;
    }
}
