package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.BusinessException;
import com.picksa.picksaserver.global.exception.ErrorCode;

public class AuthorizationException extends BusinessException {

    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
