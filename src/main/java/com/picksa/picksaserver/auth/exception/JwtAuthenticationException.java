package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.AuthenticationException;
import com.picksa.picksaserver.global.exception.ErrorCode;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
