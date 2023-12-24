package com.picksa.picksaserver.global.auth;

import com.picksa.picksaserver.auth.exception.AuthErrorCode;
import com.picksa.picksaserver.global.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum JwtValidationType {
    VALID_JWT,
    EMPTY_JWT,
    INVALID_JWT_SIGNATURE(AuthErrorCode.TOKEN_INVALID),
    INVALID_JWT(AuthErrorCode.TOKEN_INVALID),
    EXPIRED_JWT(AuthErrorCode.TOKEN_EXPIRED),
    UNSUPPORTED_JWT(AuthErrorCode.TOKEN_INVALID);

    private ErrorCode errorCode;

    JwtValidationType(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
