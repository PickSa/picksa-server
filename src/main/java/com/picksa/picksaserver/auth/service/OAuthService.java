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

}
