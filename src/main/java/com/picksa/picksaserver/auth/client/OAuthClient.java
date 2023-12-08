package com.picksa.picksaserver.auth.client;
import com.picksa.picksaserver.auth.dto.OAuthUserInfoResponse;

public interface OAuthClient {

    String getAccessToken(String authCode);

    OAuthUserInfoResponse getPlatformUserInfo(String accessToken);

}
