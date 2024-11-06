package com.example.ginc.mideum.domain.account.controller;

import java.time.Duration;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.controller.port.AuthenticationService;
import com.example.ginc.domain.account.controller.response.SignInResponse;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.domain.SignIn;
import com.example.ginc.domain.account.domain.SignUp;
import com.example.ginc.domain.account.infrastructure.entity.type.Gender;
import com.example.ginc.domain.account.service.port.BCryptPasswordEncoderService;
import com.example.ginc.util.auth.CookieUtil;
import com.example.ginc.util.auth.infrastructure.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "/initData/account-controller-test-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/initData/delete-all-data.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private BCryptPasswordEncoderService bCryptPasswordEncoderService;
    @MockBean
    private AuthenticationService authenticationService;
    private final ObjectMapper objectMapper=new ObjectMapper();

    @Test
    @Transactional
    void 사용자의_신규_회원_등록() throws Exception{
//        given
        SignUp request = SignUp.builder()
                .username("tester3")
                .password("test12341")
                .name("철수")
                .phoneNumber("01000000000")
                .email("testEmail3@test.com")
                .gender(Gender.MALE)
                .birth("1999-09-09")
                .build();

        when(bCryptPasswordEncoderService.encrypt(anyString())).thenAnswer(invocation -> invocation.getArgument(0));

//        when
//        then
        mockMvc.perform(post("/api/account/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value("Created"))
                .andExpect(jsonPath("$.resultType").value("SUCCESS"))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.data.message").value("No data available"));
    }

    @Test
    void 등록된_사용자의_로그인() throws Exception{
//        given
        SignIn request = SignIn.builder()
                        .username("tester1")
                        .password("test1234")
                        .build();

        BDDMockito.given(bCryptPasswordEncoderService.matches(anyString(), anyString())).willReturn(true);

        BDDMockito.given(authenticationService.authenticateAndSetTokens(anyString(), any(HttpServletRequest.class), any(HttpServletResponse.class)))
                .willAnswer(invocation -> {
                    HttpServletResponse response = invocation.getArgument(2);
                    CookieUtil.addCookie(response, "access_token", "mockAccessToken", (int) Duration.ofHours(1).toSeconds());
                    CookieUtil.addCookie(response, "refresh_token", "mockRefreshToken", (int) Duration.ofDays(1).toSeconds());
                    return SignInResponse.from(accountService.getById(1L));
                });

//        when
//        then
        mockMvc.perform(post("/api/account/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.resultType").value("SUCCESS"))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.data.username").value("tester1"))
                .andExpect(jsonPath("$.data.gender").value("MALE"))
                .andExpect(jsonPath("$.data.name").value("John Doe"))
                .andExpect(jsonPath("$.data.email").value("testuser@example.com"))
                .andExpect(cookie().value("access_token", "mockAccessToken"))
                .andExpect(cookie().value("refresh_token", "mockRefreshToken"));
    }

    @Test
    void 등록된_비밀번호와_일치하지_않는_값으로_로그인() throws Exception{
//        given
        SignIn request = SignIn.builder()
                .username("tester1")
                .password("test123422222")
                .build();

        BDDMockito.given(bCryptPasswordEncoderService.matches(anyString(), anyString())).willReturn(false);

//        when
//        then
        mockMvc.perform(post("/api/account/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("패스워드를 잘못 입력하셨습니다."))
                .andExpect(jsonPath("$.resultType").value("EXCEPTION"))
                .andExpect(jsonPath("$.errorCode").value("INVALID_PASSWORD"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void 로그아웃_시_헤더의_토큰_삭제() throws Exception {
//        given
        UserDomainEntity user = accountService.getById(1L);

        String refreshToken = jwtTokenProvider.generateToken(user, Duration.ofDays(1));
        String accessToken = jwtTokenProvider.generateToken(user, Duration.ofHours(1));

//        when
//        then
        mockMvc.perform(post("/api/account/logout")
                        .cookie(new Cookie("refresh_token", refreshToken))
                        .cookie(new Cookie("access_token", accessToken)))
                .andExpect(status().isOk())
                .andExpect(cookie().value("refresh_token", ""))
                .andExpect(cookie().value("access_token", ""));
    }
}