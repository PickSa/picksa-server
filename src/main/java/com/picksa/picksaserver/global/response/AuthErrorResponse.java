package com.picksa.picksaserver.global.response;

import com.picksa.picksaserver.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthErrorResponse {

    private String code;
    private String message;

    private AuthErrorResponse(final ErrorCode errorCode) {
        this.message = errorCode.message();
        this.code = errorCode.code();
    }

    public static AuthErrorResponse from(final ErrorCode errorCode) {
        return new AuthErrorResponse(errorCode);
    }

}
