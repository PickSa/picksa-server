package com.picksa.picksaserver.auth.service;

import com.picksa.picksaserver.auth.dto.OAuthUserInfoResponse;
import com.picksa.picksaserver.auth.dto.SignInResponse;
import com.picksa.picksaserver.auth.exception.AuthenticationUserNotRegisteredException;
import com.picksa.picksaserver.global.auth.JwtProvider;
import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.picksa.picksaserver.auth.exception.AuthErrorCode.USER_NOT_REGISTERED;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final AuthCodeRequestProvider authCodeRequestProvider;
    private final UserRepository userRepository;
    private final OAuthClientService oAuthClientService;
    private final JwtProvider jwtProvider;

    public String getAuthCodeRequestUrl() {
        return authCodeRequestProvider.provideAuthCodeRequestUrl();
    }

    public SignInResponse signIn(String authCode) {
        OAuthUserInfoResponse userInfo = oAuthClientService.getUserInfo(authCode);

        UserEntity user = userRepository.findByEmail(userInfo.getEmail())
                .orElseThrow(() -> new AuthenticationUserNotRegisteredException(USER_NOT_REGISTERED));

        String accessToken = jwtProvider.provideAccessToken(user);

        return SignInResponse.from(accessToken);
    }

}
