package com.picksa.picksaserver.applicant.repository;

import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long>, ApplicantQueryRepository {

    default ApplicantEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new IllegalArgumentException("[Error] 해당 지원자가 존재하지 않습니다."));
    }

}
