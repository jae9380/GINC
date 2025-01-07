package com.example.ginc.domain.account.service.port;

public interface MailSender {
    void send(String email, String code);
}

