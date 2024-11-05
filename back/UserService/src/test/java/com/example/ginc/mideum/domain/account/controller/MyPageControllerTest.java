package com.example.ginc.mideum.domain.account.controller;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.util.auth.infrastructure.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "/initData/account-controller-test-data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/initData/delete-all-data.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class MyPageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 자신의_정보_변경하기() throws Exception {
//        when
        UserDomainEntity user = accountService.getById(1L);

        String refreshToken = jwtTokenProvider.generateToken(user, Duration.ofDays(1));
        String accessToken = jwtTokenProvider.generateToken(user, Duration.ofHours(1));

        UpdateRequest request = UpdateRequest.builder()
                .password("testtest")
                .name("짱구")
                .birth("2011-11-11")
                .build();

//        then
//        given
        mockMvc.perform(put("/api/info")
                        .cookie(new Cookie("refresh_token", refreshToken))
                        .cookie(new Cookie("access_token", accessToken))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(204))
                .andExpect(jsonPath("$.message").value("No Content"))
                .andExpect(jsonPath("$.resultType").value("SUCCESS"))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.data.message").value("No data available"));

        UserDomainEntity result = accountService.getById(1L);

        assertThat(result.getUsername()).isEqualTo("tester1");
        assertThat(result.getPassword()).isEqualTo("testtest");
        assertThat(result.getName()).isEqualTo("짱구");
        assertThat(result.getBirth()).isEqualTo("2011-11-11");
    }

    @Test
    void 자신의_정보_불러오기() throws Exception {
//        when
        UserDomainEntity user = accountService.getById(1L);

        String refreshToken = jwtTokenProvider.generateToken(user, Duration.ofDays(1));
        String accessToken = jwtTokenProvider.generateToken(user, Duration.ofHours(1));

//        then
//        given
        mockMvc.perform(get("/api/info")
                        .cookie(new Cookie("refresh_token", refreshToken))
                        .cookie(new Cookie("access_token", accessToken)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.resultType").value("SUCCESS"))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.data.username").value("tester1"))
                .andExpect(jsonPath("$.data.password").value("test1234"))
                .andExpect(jsonPath("$.data.name").value("John Doe"))
                .andExpect(jsonPath("$.data.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.data.email").value("testuser@example.com"))
                .andExpect(jsonPath("$.data.gender").value("MALE"))
                .andExpect(jsonPath("$.data.birth").value("1990-01-01"))
                .andExpect(jsonPath("$.data.authenticated").value("false"))
                .andExpect(jsonPath("$.data.createdAt").value("2024-11-04"))
                .andExpect(jsonPath("$.data.modifiedAt").isEmpty());

    }
}