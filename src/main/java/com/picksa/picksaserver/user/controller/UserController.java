package com.picksa.picksaserver.user.controller;

import com.picksa.picksaserver.global.auth.CustomUserDetails;
import com.picksa.picksaserver.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserResponse response = UserResponse.from(userDetails.getUserEntity());
        return ResponseEntity.ok(response);
    }

}
