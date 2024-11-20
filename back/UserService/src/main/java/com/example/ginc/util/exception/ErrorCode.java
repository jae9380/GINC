package com.example.ginc.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Account
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디 입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 회원정보가 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "패스워드를 잘못 입력하셨습니다."),

    // Garage
    GARAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 차고 정보가 없습니다.");

    private final HttpStatus status;
    private final String message;
}
