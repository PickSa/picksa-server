package com.picksa.picksaserver.applicant.repository;

import com.picksa.picksaserver.applicant.domain.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findByApplicantId(Long applicantId);

}
