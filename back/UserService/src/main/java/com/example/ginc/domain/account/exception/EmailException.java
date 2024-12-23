package com.example.ginc.domain.account.exception;

import com.example.ginc.util.exception.ErrorCode;
import com.example.ginc.util.exception.GincException;

import static com.example.ginc.util.exception.ErrorCode.*;

public class EmailException extends GincException {

    public EmailException(ErrorCode e) {
        super(e);
    }

    public static class AuthCodeNotFoundException extends EmailException {
        public AuthCodeNotFoundException() {
            super(AUTHCODE_NOT_FOUND);
        }
    }

    public static class AuthCodeInconsistencyException extends EmailException {
        public AuthCodeInconsistencyException() {
            super(AUTHCODE_INCONSISTENCY);
        }
    }
}

