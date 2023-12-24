package com.picksa.picksaserver.auth.oAuth.service;

import com.picksa.picksaserver.auth.oAuth.config.GoogleOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GoogleAuthCodeRequestProvider implements AuthCodeRequestProvider {

    private static final String AUTHORIZATION_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    private final GoogleOAuthProperties properties;

    @Override
    public String provideAuthCodeRequestUrl() {
        return UriComponentsBuilder
                .fromUriString(AUTHORIZATION_URL)
                .queryParam("client_id", properties.getClientId())
                .queryParam("response_type", properties.getResponseType())
                .queryParam("redirect_uri", properties.getRedirectUri())
                .queryParam("scope", properties.getScope())
                .build().toUriString();
    }

}
