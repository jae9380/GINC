package com.example.ginc.domain.garage.exception;

import com.example.ginc.util.exception.ErrorCode;
import com.example.ginc.util.exception.GincException;

import static com.example.ginc.util.exception.ErrorCode.*;

public class GarageException extends GincException {

    public GarageException(ErrorCode e) {
        super(e);
    }
    public static class GarageNotFoundException extends GarageException {
        public GarageNotFoundException() {
            super(GARAGE_NOT_FOUND);
        }
    }
}

