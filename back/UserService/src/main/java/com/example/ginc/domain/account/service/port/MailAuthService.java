package com.example.ginc.domain.account.service.port;

public interface MailAuthService {
    void send(String email, Long user_id);
}
