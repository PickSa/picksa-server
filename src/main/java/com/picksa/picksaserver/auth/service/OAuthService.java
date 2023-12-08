package com.picksa.picksaserver.auth.service;

import com.picksa.picksaserver.auth.dto.OAuthUserInfoResponse;
import com.picksa.picksaserver.auth.dto.SignInResponse;
import com.picksa.picksaserver.auth.exception.AuthenticationUserNotRegisteredException;
import com.picksa.picksaserver.global.auth.JwtProvider;
import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final AuthCodeRequestProvider authCodeRequestProvider;
    private final ManagerJpaRepository managerJpaRepository;
    private final OAuthClientService oAuthClientService;
    private final JwtProvider jwtProvider;

    public String getAuthCodeRequestUrl() {
        return authCodeRequestProvider.provideAuthCodeRequestUrl();
    }

    public SignInResponse signIn(String authCode) {
        OAuthUserInfoResponse userInfo = oAuthClientService.getUserInfo(authCode);

        ManagerEntity user = managerJpaRepository.findByEmail(userInfo.getEmail())
                .orElseThrow(() -> new AuthenticationUserNotRegisteredException("등록된 사용자가 아닙니다."));

        String accessToken = jwtProvider.provideAccessToken(user);

        return SignInResponse.from(accessToken);
    }

}
