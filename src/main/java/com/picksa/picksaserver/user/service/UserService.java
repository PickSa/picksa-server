package com.picksa.picksaserver.user.service;

import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.dto.response.UserResponse;
import com.picksa.picksaserver.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserResponse getUser() {
        Long userId = getUserId();
        UserEntity user = userJpaRepository.findByIdOrThrow(userId);
        return UserResponse.from(user);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Long.valueOf(authentication.getPrincipal().toString());
    }


}
