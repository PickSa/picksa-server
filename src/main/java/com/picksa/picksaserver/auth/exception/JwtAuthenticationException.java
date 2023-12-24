package com.picksa.picksaserver.auth.exception;

import com.picksa.picksaserver.global.exception.ErrorCode;
import io.jsonwebtoken.Jwt;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends PicksaAuthenticationException {

    public JwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
