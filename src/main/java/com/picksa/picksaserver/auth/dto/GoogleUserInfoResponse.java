package com.picksa.picksaserver.auth.dto;

public class GoogleUserInfoResponse implements OAuthUserInfoResponse {

    private String email;

    @Override
    public String getEmail() {
        return this.email;
    }

}
