package com.picksa.picksaserver.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GoogleOAuthToken(
        String accessToken,
        String expiresIn,
        String refreshToken,
        String scope,
        String tokenType,
        String idToken
) {
}
