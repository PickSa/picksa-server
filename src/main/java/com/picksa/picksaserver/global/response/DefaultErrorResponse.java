package com.picksa.picksaserver.global.response;

import lombok.Getter;

@Getter
public class DefaultErrorResponse {

    private String message;

    public DefaultErrorResponse(String message) {
        this.message = message;
    }

    public static DefaultErrorResponse from(final String message) {
        return new DefaultErrorResponse(message);
    }

}
