package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.exception.AccountException;
import com.example.ginc.domain.account.infrastructure.entity.AuthCode;
import com.example.ginc.domain.account.service.port.MailAuthRepository;
import com.example.ginc.domain.account.service.port.MailAuthService;
import com.example.ginc.domain.account.service.port.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MailAuthServiceImpl implements MailAuthService {
    private final MailSender mailSender;
    private final MailAuthRepository mailAuthRepository;

    @Override
    public void send(String email, Long user_id) {
        mailSender.send(email, createCode(user_id));
    }

    @Transactional
    protected String createCode(Long user_id) {
        String randomKey = RandomStringUtils.randomAlphanumeric(8);
        if (findByUserId(user_id).isPresent()) {
            AuthCode authCodeEntity = getByUserId(user_id);
            mailAuthRepository.save( authCodeEntity.change(randomKey));

            return randomKey;
        }

        AuthCode authCode = AuthCode.create(user_id, randomKey);
        mailAuthRepository.save(authCode);
        return randomKey;
/*
        TODO :
        현재 회원가입 시 이메일 인증코드가 발생이 된다. 그리고 재회원가입 시 기존에 해당 유저의 id값에 해당하는 인증 코드가 존재할 경우
        새로운 인증 코드로 변경되도록 했다.
        하지만, 해당 로직에서 확인 가능한 부분은 재회워가입 시 이미 존재하는 유저의 Id값이라고 예외가 발생하는 문제점이 있다.

        기존의 방식에서 인증을 위한 이메일 재발송 로직을 분리
 */
    }

    private AuthCode getByUserId(Long user_id) {
        return findByUserId(user_id)
                .orElseThrow(AccountException.MemberNotFoundException::new); // TODO : 예외 임시로 사용
    }

    private Optional<AuthCode> findByUserId(long user_id) {
        return mailAuthRepository.findByUserId(user_id);
    }

}
