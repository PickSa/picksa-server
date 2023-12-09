package com.picksa.picksaserver.auth.controller;

import com.picksa.picksaserver.auth.dto.SignInResponse;
import com.picksa.picksaserver.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final OAuthService oAuthService;

    @GetMapping
    public ResponseEntity<Void> getAuthCodeRequestUrl() {
        String authCodeRequestUrl = oAuthService.getAuthCodeRequestUrl();
        return ResponseEntity.ok()
                .header("Location", authCodeRequestUrl)
                .build();
    }

    @GetMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestParam String code) {
        SignInResponse response = oAuthService.signIn(code);
        return ResponseEntity.ok(response);
    }

}
