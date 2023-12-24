package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.ErrorCode;

public class AuthenticationUserNotExistException extends AuthenticationException {

    public AuthenticationUserNotExistException(ErrorCode errorCode) {
        super(errorCode);
    }

}
