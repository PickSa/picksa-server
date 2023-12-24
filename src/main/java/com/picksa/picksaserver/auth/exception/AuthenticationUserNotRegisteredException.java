package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.ErrorCode;

public class AuthenticationUserNotRegisteredException extends PicksaAuthenticationException {
    public AuthenticationUserNotRegisteredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
