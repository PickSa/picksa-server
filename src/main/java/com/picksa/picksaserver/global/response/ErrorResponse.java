package com.picksa.picksaserver.global.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picksa.picksaserver.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private String code;
    private String message;

    private ErrorResponse(final ErrorCode errorCode) {
        this.message = errorCode.message();
        this.code = errorCode.code();
    }

    public static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

}
