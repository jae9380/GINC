package com.example.ginc.domain.account.exception;

import com.example.ginc.util.exception.ErrorCode;
import com.example.ginc.util.exception.GincException;

import static com.example.ginc.util.exception.ErrorCode.*;

public class AccountException extends GincException {

    public AccountException(ErrorCode e) {
        super(e);
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

