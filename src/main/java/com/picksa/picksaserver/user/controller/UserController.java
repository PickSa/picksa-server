package com.picksa.picksaserver.user.controller;

import com.picksa.picksaserver.user.dto.response.UserResponse;
import com.picksa.picksaserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        UserResponse response = userService.getUser();
        return ResponseEntity.ok(response);
    }

}
