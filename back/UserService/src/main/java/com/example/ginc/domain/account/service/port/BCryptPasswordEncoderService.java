package com.example.ginc.domain.account.service.port;

public interface BCryptPasswordEncoderService {
    String encrypt(String rawPassword);
    boolean matches(String rawPassword, String encryptedPassword);
}
