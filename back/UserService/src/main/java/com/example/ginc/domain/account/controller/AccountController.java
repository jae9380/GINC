package com.example.ginc.domain.account.controller;

import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignInResponse;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.service.AccountService;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.Empty;
import com.example.ginc.util.auth.CookieUtil;
import com.example.ginc.util.auth.MemberDetails;
import com.example.ginc.util.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ApiResponse<Empty> signup(@RequestBody @Valid SignUpRequest request) {
        accountService.signup(request);
        return ApiResponse.created();
    }

    @PutMapping("/updateInfo/{id}")
    public ApiResponse<Empty> updateInfo(@RequestBody @Valid UpdateRequest request,  @PathVariable(name = "id") Long id) {
        accountService.updateUserInfo(id, request);
        return ApiResponse.noContent();
    }

    @PostMapping ("/login")
    public ApiResponse<SignInResponse> login(@RequestBody @Valid SignInRequest signInRequest,
                                             HttpServletRequest request, HttpServletResponse response) {
        accountService.login(signInRequest);
        SignInResponse signInResponse = authenticationService.authenticateAndSetTokens(signInRequest.username(), request, response);

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
