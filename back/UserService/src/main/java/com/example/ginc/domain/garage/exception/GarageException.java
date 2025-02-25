package com.example.ginc.domain.garage.exception;



import com.example.apiresponse.exception.ErrorCode;
import com.example.apiresponse.exception.GincException;

import static com.example.apiresponse.exception.ErrorCode.*;

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

