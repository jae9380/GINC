package com.example.ginc.domain.account.controller;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.controller.response.MyProfileResponse;
import com.example.ginc.domain.account.domain.Update;
import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import com.example.ginc.util.auth.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class MyPageController {
    private final AccountService accountService;

    @GetMapping
    public ApiResponse<MyProfileResponse> getInfo(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ApiResponse.ok(MyProfileResponse.from(accountService.getById(memberDetails.getId())));
    }

    @PutMapping
    public ApiResponse<Empty> updateInfo(@AuthenticationPrincipal MemberDetails memberDetails,
                                         @RequestBody @Valid Update request) {
        accountService.updateUserInfo(memberDetails.getId(), request);
        return ApiResponse.noContent();
    }
}
