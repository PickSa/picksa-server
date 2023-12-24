package com.picksa.picksaserver.auth.oAuth.service;

import com.picksa.picksaserver.auth.oAuth.client.OAuthClient;
import com.picksa.picksaserver.auth.oAuth.dto.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthClientService {

    private final OAuthClient oAuthClient;

    public OAuthUserInfoResponse getUserInfo(String authCode) {
        String accessToken = oAuthClient.getAccessToken(authCode);
        return oAuthClient.getPlatformUserInfo(accessToken);
    }

}
