package com.example.ginc.domain.account.controller;

import com.example.ginc.domain.account.dto.SignUpRequest;
import com.example.ginc.domain.account.dto.UpdateRequest;
import com.example.ginc.domain.account.service.AccountService;
import com.example.ginc.util.ApiResponse;
import com.example.ginc.util.Empty;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

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

    /* TODO
    * Login
    * Logout
     */
}
