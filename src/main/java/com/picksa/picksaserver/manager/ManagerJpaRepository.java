package com.picksa.picksaserver.manager;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerJpaRepository extends JpaRepository <ManagerEntity, Long> {

    default ManagerEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new EntityNotFoundException("[Error] 해당 운영진이 존재하지 않습니다."));
    }
}
