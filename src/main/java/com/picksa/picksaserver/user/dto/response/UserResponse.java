package com.picksa.picksaserver.user.dto.response;

import com.picksa.picksaserver.user.UserEntity;

public record UserResponse(
        String name,
        String role
) {

    public static UserResponse from(UserEntity user) {
        String role = user.getPosition().getPositionName();
        return new UserResponse(user.getName(), role);
    }

}
