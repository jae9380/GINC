package com.example.ginc.util.configuration;

import com.example.ginc.domain.account.exception.AccountException;
import com.example.apiresponse.exception.ErrorCode;
import com.example.apiresponse.apiResponse.ApiResponse;
import com.example.apiresponse.apiResponse.Empty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionConfig {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ApiResponse<Empty>> handleAccountException(AccountException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Empty> response = ApiResponse.error(errorCode.getStatus(), errorCode.name(), errorCode.getMessage());
        return new ResponseEntity<>(response, errorCode.getStatus());
    }
}
