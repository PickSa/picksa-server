package com.picksa.picksaserver.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    default UserEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new IllegalArgumentException("[Error] 해당 운영진이 존재하지 않습니다."));
    }

    Optional<UserEntity> findByEmail(String email);

}
