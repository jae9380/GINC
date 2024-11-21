package com.example.ginc.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Global
    BAD_INPUT_FORMAT(HttpStatus.BAD_REQUEST, "입력 형식이 틀렸습니다."),

    // Account
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디 입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 회원정보가 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "패스워드를 잘못 입력하셨습니다."),

    // Garage
    GARAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 차고 정보가 없습니다."),
    CAR_NOT_FOUND(HttpStatus.NOT_FOUND, "등록된 차량 정보가 없습니다."),
    REFUELING_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "등록된 주유 기록이 없습니다.");

    private final HttpStatus status;
    private final String message;
}
