package com.picksa.picksaserver.manager;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerJpaRepository extends JpaRepository <ManagerEntity, Long> {

    default ManagerEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new IllegalArgumentException("[Error] 해당 운영진이 존재하지 않습니다."));
    }
}
