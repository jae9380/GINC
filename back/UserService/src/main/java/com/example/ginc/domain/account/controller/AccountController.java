package com.example.ginc.domain.account.controller;

import com.example.ginc.util.ApiResponse;
import com.example.ginc.util.Empty;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @PatchMapping("/signup")
    public ApiResponse<Empty> signup() {

        return ApiResponse.created();
    }
}
