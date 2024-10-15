package com.example.ginc.util.apiResponse;

import com.example.ginc.util.Empty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.example.ginc.util.apiResponse.ApiResultType.*;

@Getter
public class ApiResponse<T>{
    private final int statusCode;
    @NotNull
    private final String message;
    @NotNull
    private final ApiResultType resultType;
    private final String errorCode;
    @NotNull
    private final T data;

    private ApiResponse(int statusCode, String message, ApiResultType resultType, T data) {
        this(statusCode, message, resultType, null, data);
    }

    private ApiResponse(int statusCode, String message, ApiResultType resultType, String errorCode, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.resultType = resultType;
        this.errorCode = errorCode;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                SUCCESS,
                data
        );
    }

    public static ApiResponse<Empty> created() {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                SUCCESS,
                new Empty()
        );
    }

    public static ApiResponse<Empty> noContent() {
        return new ApiResponse<>(
                HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT.getReasonPhrase(),
                SUCCESS,
                new Empty()
        );
    }

    // 에러 응답 추가
    public static <T> ApiResponse<T> error(HttpStatus status, String errorCode, String message) {
        return new ApiResponse<>(
                status.value(),
                message,
                EXCEPTION,
                errorCode,
                null
        );
    }
}
