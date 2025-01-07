package com.example.ginc.domain.account.service;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.controller.response.MyProfileResponse;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.domain.SignIn;
import com.example.ginc.domain.account.domain.SignUp;
import com.example.ginc.domain.account.domain.Update;
import com.example.ginc.domain.account.event.SignupEvent;
import com.example.ginc.domain.account.service.port.AccountRepository;
import com.example.ginc.domain.account.service.port.BCryptPasswordEncoderService;
import com.example.ginc.domain.account.exception.AccountException;
import com.example.service.port.ClockHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final ApplicationEventPublisher publisher;
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoderService bCryptPasswordEncoderService;
    private final ClockHolder clockHolder;
    private final MailAuthServiceImpl mailAuthService;

    @Override
    @Transactional
    public void signup(SignUp request) {
        validationByUsername(request.username());

        String encryptedPassword = bCryptPasswordEncoderService.encrypt(request.password());
        UserDomainEntity userDomain = UserDomainEntity.create(request, encryptedPassword, clockHolder);
        userDomain = accountRepository.save(userDomain);
        log.info("User {} successfully signed up.", request.username());

//      TODO : 회원가입 시 이메일 발송 로직을 추가하니 기존 응답시간에 비하여 많이 느려진다. 그렇기 때문에 이메일 발송 로직을 분리
        publisher.publishEvent(new SignupEvent(this, request.email(), userDomain.getId()));
    }

    @Override
    @Transactional
    public void updateUserInfo(Long id, Update request) {
        UserDomainEntity userDomainEntity = getById(id);
        userDomainEntity = userDomainEntity.update(request, clockHolder);
        accountRepository.save( userDomainEntity);
    }

    @Override
    public void login(SignIn request) {
        UserDomainEntity userDomainEntity = getByUsername(request.username());

        if (!bCryptPasswordEncoderService.matches(request.password(), userDomainEntity.getPassword())) {
            throw new AccountException.InvalidPasswordException();
        }
    }

    @Override
    public UserDomainEntity getByUsername(String username) {
        return findByUsername(username)
                .orElseThrow(AccountException.MemberNotFoundException::new);
    }

    @Override
    public UserDomainEntity getById(Long id) {
        return findById(id)
                .orElseThrow(AccountException.MemberNotFoundException::new);
    }

    @Override
    public MyProfileResponse getMyProfileById(Long id) {
        UserDomainEntity entity = getById(id);
        return MyProfileResponse.from(entity,clockHolder);

    }

    @Override
    @Transactional
    public String certificationAtEmail(Long id, String authCode) {
        mailAuthService.certification(id, authCode);
        accountRepository.save(getById(id).certification());
        return "인증에 성공했습니다.";
    }

    private void validationByUsername(String username) {
        findByUsername(username).ifPresent(member -> {
            throw new AccountException.DuplicateUsernameException();
        });
    }
    private Optional<UserDomainEntity> findById(long id) {
        return accountRepository.findById(id);
    }

    private Optional<UserDomainEntity> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
