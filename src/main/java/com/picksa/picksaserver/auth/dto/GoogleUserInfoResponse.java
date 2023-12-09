package com.picksa.picksaserver.auth.dto;

import com.picksa.picksaserver.auth.dto.OAuthUserInfoResponse;

public class GoogleUserInfoResponse implements OAuthUserInfoResponse {

    private String email;

    @Override
    public String getEmail() {
        return this.email;
    }

}
