package com.picksa.picksaserver.auth.service;

import com.picksa.picksaserver.auth.client.OAuthClient;
import com.picksa.picksaserver.auth.dto.OAuthUserInfoResponse;
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
