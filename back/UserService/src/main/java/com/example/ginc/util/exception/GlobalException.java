package com.example.ginc.util.exception;

import com.example.ginc.domain.garage.exception.GarageException;

import static com.example.ginc.util.exception.ErrorCode.BAD_INPUT_FORMAT;
import static com.example.ginc.util.exception.ErrorCode.NOT_AUTHORIZED;

public class GlobalException extends GincException {
    public GlobalException(ErrorCode e) {
        super(e);
    }
    public static class BadInputFormatException extends GarageException {
        public BadInputFormatException() {
            super(BAD_INPUT_FORMAT);
        }
    }
    public static class NotAuthorizedException extends GarageException {
        public NotAuthorizedException() {
            super(NOT_AUTHORIZED);
        }
    }
}
