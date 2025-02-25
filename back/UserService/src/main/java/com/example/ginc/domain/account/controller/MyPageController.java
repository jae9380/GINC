package com.example.ginc.domain.account.controller;

import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.domain.account.controller.response.MyProfileResponse;
import com.example.ginc.domain.account.domain.Update;
import com.example.apiresponse.apiResponse.Empty;
import com.example.apiresponse.apiResponse.ApiResponse;
import com.example.ginc.util.auth.MemberDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class MyPageController {
    private final AccountService accountService;

    @GetMapping
    public ApiResponse<MyProfileResponse> getInfo(@AuthenticationPrincipal MemberDetails memberDetails) {
        return ApiResponse.ok(accountService.getMyProfileById(memberDetails.getId()));
    }

    @PutMapping
    public ApiResponse<Empty> updateInfo(@AuthenticationPrincipal MemberDetails memberDetails,
                                         @RequestBody @Valid Update request) {
        accountService.updateUserInfo(memberDetails.getId(), request);
        return ApiResponse.noContent();
    }

    @PostMapping("/email_certification/{AuthCode}")
    public ApiResponse<String> emailCertification(@AuthenticationPrincipal MemberDetails memberDetails,
                                                 @PathVariable(name = "AuthCode") String authCode) {
        return ApiResponse.ok(accountService.certificationAtEmail(memberDetails.getId(), authCode));
        //    TODO : 이메일 인증 방법을 Code 입력 방법 & 특정 페이지로 이동하여 인증하는 방법 中 선택
    }

    @GetMapping("/1")
    public String test(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader,
                       @CookieValue(value = "access_token", required = false) String accessTokenFromCookie) {

        StringBuilder result = new StringBuilder("Token Test Result:\n");

        // 헤더에서 추출한 토큰 처리
        String tokenFromHeader = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            tokenFromHeader = authorizationHeader.substring(7); // "Bearer " 제거
            result.append("Token from Header: ").append(tokenFromHeader).append("\n");
        } else {
            result.append("No Token found in Header\n");
        }

        // 쿠키에서 추출한 토큰 처리
        if (accessTokenFromCookie != null) {
            result.append("Token from Cookie: ").append(accessTokenFromCookie).append("\n");
        } else {
            result.append("No Token found in Cookie\n");
        }

        return result.toString();
    }
}
