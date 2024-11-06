package com.example.ginc.domain.account.controller.port;

import com.example.ginc.domain.account.controller.response.SignInResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.Duration;

public interface AuthenticationService {
    SignInResponse authenticateAndSetTokens(String username, HttpServletRequest request, HttpServletResponse response);
    void addTokenToCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String token, Duration duration);
}
