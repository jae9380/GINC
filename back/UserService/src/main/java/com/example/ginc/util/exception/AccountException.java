package com.example.ginc.util.exception;

import static com.example.ginc.util.exception.ErrorCode.*;

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

    public static class InvalidPasswordException extends AccountException {
        public InvalidPasswordException() {
            super(INVALID_PASSWORD);
        }
    }
}

