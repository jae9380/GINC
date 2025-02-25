package com.example.ginc.domain.account.exception;

import com.example.apiresponse.exception.ErrorCode;
import com.example.apiresponse.exception.GincException;

import static com.example.apiresponse.exception.ErrorCode.*;

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
    public static class InitiateEmailRequestException extends EmailException {
        public InitiateEmailRequestException() {
            super(INITIATE_EMAIL_REQUEST);
        }
    }

}

