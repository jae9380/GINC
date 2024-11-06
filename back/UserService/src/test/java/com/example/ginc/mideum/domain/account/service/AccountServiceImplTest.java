package com.example.ginc.mideum.domain.account.service;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.domain.SignIn;
import com.example.ginc.domain.account.domain.SignUp;
import com.example.ginc.domain.account.domain.Update;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.service.port.BCryptPasswordEncoderService;
import com.example.ginc.domain.account.exception.AccountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@SqlGroup({
        @Sql(value = "/initData/account-service-test-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/initData/delete-all-data.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;
    @MockBean
    private BCryptPasswordEncoderService bCryptPasswordEncoderService;

    @Test
    void signup으로_신규_유저_생성() {
//        given
        SignUp request = SignUp.builder()
                .username("tester3")
                .password("test1234")
                .name("철수")
                .phoneNumber("01000000000")
                .email("testEmail3@test.com")
                .gender(Gender.MALE)
                .birth("1999-09-09")
                .build();

        when(bCryptPasswordEncoderService.encrypt(anyString())).thenAnswer(invocation -> invocation.getArgument(0));

//        when
        accountService.signup(request);

//        then
        UserDomainEntity result = accountService.getByUsername("tester3");

        assertThat(result.getId()).isEqualTo(3L);
        assertThat(result.getEmail()).isEqualTo("testEmail3@test.com");
        assertThat(result.getPassword()).isEqualTo("test1234");
    }

    @Test
    void signup으로_중복돤_Username으로_신규_유저_생성_시_예외_발생() {
//        given
        SignUp request = SignUp.builder()
                .username("tester2")
                .password("test1234")
                .name("철수")
                .phoneNumber("01000000000")
                .email("testEmail3@test.com")
                .gender(Gender.MALE)
                .birth("1999-09-09")
                .build();

//        when
//        then
        assertThatThrownBy(() -> {
            accountService.signup(request);
        }).isInstanceOf(AccountException.DuplicateUsernameException.class);
    }

    @Test
    void updateUserInfo로_유저의_정보_변경() {
//        given
        Update update = Update.builder()
                .password("1test")
                .name("맹구")
                .birth("2020-02-02")
                .build();

//        when
        accountService.updateUserInfo(1L, update);

//        then
        UserDomainEntity result = accountService.getById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("맹구");
        assertThat(result.getEmail()).isEqualTo("testuser@example.com");
        assertThat(result.getBirth()).isEqualTo("2020-02-02");
    }

    @Test
    void updateUserInfo로_없는_유저의_정보_변경_시_예외_발생() {
//        given
        Update request = Update.builder()
                .password("1test")
                .name("맹구")
                .birth("2020-02-02")
                .build();

//        when
//        then
        assertThatThrownBy(() -> {
            accountService.updateUserInfo(3L, request);
        }).isInstanceOf(AccountException.MemberNotFoundException.class);
    }

    @Test
    void login_시_틀린_아이디로_예외_발생() {
//        given
        SignIn request = SignIn.builder()
                .username("tester12")
                .password("test1234")
                .build();

//        when
//        then
        assertThatThrownBy(() -> {
            accountService.login(request);
        }).isInstanceOf(AccountException.MemberNotFoundException.class);
    }

    @Test
    void login_시_틀린_비밀번호로_예외_발생() {
//        given
        SignIn request = SignIn.builder()
                .username("tester1")
                .password("test12345")
                .build();

//        when
//        then
        assertThatThrownBy(() -> {
            accountService.login(request);
        }).isInstanceOf(AccountException.InvalidPasswordException.class);
    }

    @Test
    void login_옳은_정보로_시도() {
//        given
        SignIn request = SignIn.builder()
                .username("tester1")
                .password("test1234")
                .build();

        given(bCryptPasswordEncoderService.matches(anyString(), anyString())).willReturn(true);

//        when
//        then
        assertThatCode(() -> accountService.login(request))
                .doesNotThrowAnyException();
    }
}