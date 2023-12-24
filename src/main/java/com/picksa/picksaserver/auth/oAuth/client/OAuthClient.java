package com.picksa.picksaserver.auth.oAuth.client;
import com.picksa.picksaserver.auth.oAuth.dto.OAuthUserInfoResponse;

public interface OAuthClient {

    String getAccessToken(String authCode);

    OAuthUserInfoResponse getPlatformUserInfo(String accessToken);

}
