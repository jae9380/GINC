package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.service.port.MailAuthService;
import com.example.ginc.domain.account.service.port.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MailAuthServiceImpl implements MailAuthService {
    private final MailSender mailSender;

    @Override
    public void send(String email) {
        mailSender.send(email, createCode());
    }

    private String createCode() {
//        TODO: 간단한 8자리 Auth Code 생성로직 작성
        return "";
    }
}
