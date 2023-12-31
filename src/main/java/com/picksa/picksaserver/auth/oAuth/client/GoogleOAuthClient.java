package com.picksa.picksaserver.auth.oAuth.client;

import com.picksa.picksaserver.auth.oAuth.config.GoogleOAuthProperties;
import com.picksa.picksaserver.auth.oAuth.dto.GoogleOAuthToken;
import com.picksa.picksaserver.auth.oAuth.dto.GoogleUserInfoResponse;
import com.picksa.picksaserver.auth.oAuth.dto.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class GoogleOAuthClient implements OAuthClient {

    private static final String REQUEST_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String REQUEST_USER_INFO_URL = "https://oauth2.googleapis.com/tokeninfo";
    private final GoogleOAuthProperties properties;
    private final RestTemplate restTemplate;

    @Override
    public String getAccessToken(String authCode) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        URI requestUrl = UriComponentsBuilder
                .fromUriString(REQUEST_TOKEN_URL)
                .queryParam("client_id", properties.getClientId())
                .queryParam("client_secret", properties.getClientSecret())
                .queryParam("grant_type", properties.getGrantType())
                .queryParam("redirect_uri", properties.getRedirectUri())
                .queryParam("code", authCode)
                .build().toUri();

        GoogleOAuthToken token = restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(httpHeaders),
                GoogleOAuthToken.class
        );

        return token.accessToken();
    }

    @Override
    public OAuthUserInfoResponse getPlatformUserInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBearerAuth(accessToken);

        URI requestUrl = UriComponentsBuilder
                .fromUriString(REQUEST_USER_INFO_URL)
                .queryParam("access_token", accessToken)
                .build().toUri();

        return restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(httpHeaders),
                GoogleUserInfoResponse.class
        );
    }

}
