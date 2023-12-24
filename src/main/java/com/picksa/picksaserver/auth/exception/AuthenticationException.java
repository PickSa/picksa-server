package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.BusinessException;
import com.picksa.picksaserver.global.exception.ErrorCode;

public class AuthenticationException extends BusinessException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
