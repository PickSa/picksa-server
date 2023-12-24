package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    TOKEN_EMPTY("A001", "토큰이 존재하지 않습니다."),
    TOKEN_EXPIRED("A002", "토큰이 만료되었습니다."),
    TOKEN_INVALID("A003", "토큰이 유효하지 않습니다."),

    PLATFORM_TOKEN_REQUEST_FAILED("A004", "플랫폼에서 액세스 토큰을 획득하는 데 실패했습니다."),
    PLATFORM_USER_REQUEST_FAILED("A005", "플랫폼에서 사용자 정보를 획득하는 데 실패했습니다."),
    USER_NOT_REGISTERED("A006", "사용자가 등록되어있지 않습니다.");

    private final String code;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }

}
