package com.picksa.picksaserver.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerJpaRepository extends JpaRepository<ManagerEntity, Long> {

    default ManagerEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new IllegalArgumentException("[Error] 해당 운영진이 존재하지 않습니다."));
    }

    Optional<ManagerEntity> findByEmail(String email);

}
