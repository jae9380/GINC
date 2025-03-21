package com.example.garage.exception;

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
    public static class CarNotFoundException extends GarageException {
        public CarNotFoundException() {
            super(CAR_NOT_FOUND);
        }
    }

    public static class RefuelingRecordNotFoundException extends GarageException {
        public RefuelingRecordNotFoundException() {
            super(REFUELING_RECORD_NOT_FOUND);
        }
    }
}

