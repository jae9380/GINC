package com.example.ginc.domain.account.controller;

import com.example.ginc.domain.account.domain.SignIn;
import com.example.ginc.domain.account.controller.response.SignInResponse;
import com.example.ginc.domain.account.domain.SignUp;
import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.Empty;
import com.example.ginc.util.auth.CookieUtil;
import com.example.ginc.domain.account.controller.port.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ApiResponse<Empty> signup(@RequestBody @Valid SignUp request) {
        accountService.signup(request);
        return ApiResponse.created();
    }

    @PostMapping ("/login")
    public ApiResponse<SignInResponse> login(@RequestBody @Valid SignIn signIn,
                                             HttpServletRequest request, HttpServletResponse response) {
        accountService.login(signIn);
        SignInResponse signInResponse = authenticationService.authenticateAndSetTokens(signIn.username(), request, response);

        return ApiResponse.ok(signInResponse);
    }

    @PostMapping ("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response) {
        Stream.of("refresh_token", "access_token", "JSESSIONID")
                .forEach(cookieName -> CookieUtil.deleteCookie(request, response, cookieName));

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "로그아웃 되었습니다.");
        return ResponseEntity.ok(responseMap);
    }
}
