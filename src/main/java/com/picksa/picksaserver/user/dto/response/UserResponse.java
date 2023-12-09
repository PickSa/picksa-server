package com.picksa.picksaserver.user.dto.response;

import com.picksa.picksaserver.user.UserEntity;

public record UserResponse(
        String name
) {

    public static UserResponse from(UserEntity user) {
        return new UserResponse(user.getName());
    }

}
