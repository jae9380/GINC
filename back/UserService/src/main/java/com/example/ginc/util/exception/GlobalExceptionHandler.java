package com.example.ginc.util.exception;

import com.example.ginc.util.Empty;
import com.example.ginc.util.apiResponse.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Empty> handleValidException(MethodArgumentNotValidException e) {

        List<String> errorMessages = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        log.error(errorMessages.toString());    // 모든 유효성 검사 에러를 로그로 찍음

        CustomHttpStatus customHttpStatus = CustomHttpStatus.builder()
                .statusCode(400)
                .statusMessage(errorMessages.get(0))   // 클라이언트에게는 첫 번째 에러를 반환
                .build();

        return ApiResponse.validationException(customHttpStatus);
    }

    @ExceptionHandler(GincException.class)
    public ApiResponse<Empty> handleCustomException(GincException gincException) {

        log.error(gincException.getErrorCode() + ": " + gincException.getMessage());

        return ApiResponse.customException(gincException.getErrorCode());
    }
}
