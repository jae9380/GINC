package com.example.ginc.domain.account.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.example.ginc.domain.account.exception.ErrorCode.*;

public class AccountException extends RuntimeException {

    private final ErrorCode errorCode;

    public AccountException(ErrorCode e) {
        super(e.getMessage());
        this.errorCode = e;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public static class DuplicateUsernameException extends AccountException {
        public DuplicateUsernameException() {
            super(DUPLICATE_USERNAME);
        }
    }

    public static class MemberNotFoundException extends AccountException {
        public MemberNotFoundException() {
            super(MEMBER_NOT_FOUND);
        }
    }
}

@Getter
enum ErrorCode {
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디 입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 회원정보가 없습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}