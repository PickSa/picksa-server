package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.AuthenticationException;
import com.picksa.picksaserver.global.exception.ErrorCode;

public class AuthenticationUserNotRegisteredException extends AuthenticationException {

    public AuthenticationUserNotRegisteredException(ErrorCode errorCode) {
        super(errorCode);
    }

}
