package com.picksa.picksaserver.auth.oAuth.dto;

public record SignInResponse(
        String accessToken
) {

    public static SignInResponse from(String accessToken) {
        return new SignInResponse(accessToken);
    }

}
