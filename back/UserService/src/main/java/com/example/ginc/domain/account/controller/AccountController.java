package com.example.ginc.domain.account.controller;

import com.example.ginc.domain.account.dto.SignInRequest;
import com.example.ginc.domain.account.dto.SignInResponse;
import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.service.AccountService;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.Empty;
import com.example.ginc.util.auth.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        // TODO: 로그인 시 JWT 토큰 발행, SignInResponse 반환
        SignInResponse signInResponse = authenticationService.authenticateAndSetTokens(signInRequest.username(), request, response);

        return ApiResponse.ok(signInResponse);
    }
    /* TODO
    * Login
    * Logout
     */
}
