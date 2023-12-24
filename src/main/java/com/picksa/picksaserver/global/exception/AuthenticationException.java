package com.picksa.picksaserver.global.exception;

import com.picksa.picksaserver.global.exception.BusinessException;
import com.picksa.picksaserver.global.exception.ErrorCode;
import lombok.Getter;

public class AuthenticationException extends BusinessException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
