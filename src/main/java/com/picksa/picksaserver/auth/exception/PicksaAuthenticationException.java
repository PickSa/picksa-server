package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class PicksaAuthenticationException extends AuthenticationException {

    private final ErrorCode errorCode;

    public PicksaAuthenticationException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

}
