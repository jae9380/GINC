package com.example.ginc.util.auth.service;

import com.example.ginc.domain.account.controller.response.SignInResponse;
import com.example.ginc.domain.account.domain.UserDomainEntity;
import com.example.ginc.domain.account.infrastructure.entity.UserJpaEntity;
import com.example.ginc.domain.account.controller.port.AccountService;
import com.example.ginc.util.auth.CookieUtil;
import com.example.ginc.util.auth.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AccountService accountService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(1);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofHours(1);

    @Override
    public SignInResponse authenticateAndSetTokens(String username, HttpServletRequest request, HttpServletResponse response) {
        UserDomainEntity userDomainEntity = accountService.getByUsername(username);

        String refreshToken = jwtTokenProvider.generateToken(userDomainEntity, REFRESH_TOKEN_DURATION);
        refreshTokenService.save(userDomainEntity.getId(), refreshToken);
        addTokenToCookie(request, response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, REFRESH_TOKEN_DURATION);

        String accessToken = jwtTokenProvider.generateToken(userDomainEntity, ACCESS_TOKEN_DURATION);
        addTokenToCookie(request, response, ACCESS_TOKEN_COOKIE_NAME, accessToken, ACCESS_TOKEN_DURATION);

        return SignInResponse.from(userDomainEntity);
    }

    @Override
    public void addTokenToCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String token, Duration duration) {
        int cookieMaxAge = (int) duration.toSeconds();
        CookieUtil.deleteCookie(request, response, cookieName);
        CookieUtil.addCookie(response, cookieName, token, cookieMaxAge);
    }
}
