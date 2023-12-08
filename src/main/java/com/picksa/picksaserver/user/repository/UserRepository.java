package com.picksa.picksaserver.user.repository;

import com.picksa.picksaserver.auth.exception.AuthenticationUserNotRegisteredException;
import com.picksa.picksaserver.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    default UserEntity findByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new AuthenticationUserNotRegisteredException("등록된 운영진이 아닙니다"));
    }
    
    int countByGeneration(int generation);

}
