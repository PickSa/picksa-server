package com.picksa.picksaserver.applicant;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantJpaRepository extends JpaRepository<ApplicantEntity, Long> {

    default ApplicantEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new IllegalArgumentException("[Error] 해당 지원자가 존재하지 않습니다."));
    }

    List<ApplicantEntity> findAll();

}
