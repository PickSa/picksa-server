package com.picksa.picksaserver.global.exception;

public class AuthorizationException extends BusinessException {

    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
