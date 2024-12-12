package com.example.ginc.domain.account.eventListener;

import com.example.ginc.domain.account.event.SignupEvent;
import com.example.ginc.domain.account.infrastructure.MailSenderImpl;
import com.example.ginc.domain.account.service.port.MailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailSenderEventListener {
    private final MailAuthService mailAuthService;

    @EventListener
    public void signupEventListener(SignupEvent event) {
        log.info("Send to Mail "+event.getUser_email()+" - SignUp Event Operation");

        mailAuthService.send(event.getUser_email());
    }
}
