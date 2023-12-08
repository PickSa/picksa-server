package com.picksa.picksaserver.auth.service;

import com.picksa.picksaserver.auth.dto.OAuthUserInfoResponse;
import com.picksa.picksaserver.auth.dto.SignInResponse;
import com.picksa.picksaserver.auth.exception.AuthenticationUserNotRegisteredException;
import com.picksa.picksaserver.global.auth.JwtProvider;
import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final AuthCodeRequestProvider authCodeRequestProvider;
    private final UserJpaRepository userJpaRepository;
    private final OAuthClientService oAuthClientService;
    private final JwtProvider jwtProvider;

    public String getAuthCodeRequestUrl() {
        return authCodeRequestProvider.provideAuthCodeRequestUrl();
    }

    public SignInResponse signIn(String authCode) {
        OAuthUserInfoResponse userInfo = oAuthClientService.getUserInfo(authCode);

        UserEntity user = userJpaRepository.findByEmail(userInfo.getEmail())
                .orElseThrow(() -> new AuthenticationUserNotRegisteredException("등록된 사용자가 아닙니다."));

        String accessToken = jwtProvider.provideAccessToken(user);

        return SignInResponse.from(accessToken);
    }

}
