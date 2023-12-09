package com.picksa.picksaserver.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationUserNotRegisteredException extends AuthenticationException {

    public AuthenticationUserNotRegisteredException(String msg) {
        super(msg);
    }

}
