package com.example.ginc.util.exception;

import lombok.Getter;

@Getter
public class GincException extends RuntimeException {
    private final ErrorCode errorCode;

    public GincException(ErrorCode e) {
        super(e.getMessage());
        this.errorCode = e;
    }
}