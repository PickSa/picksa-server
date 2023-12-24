package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static com.picksa.picksaserver.evaluation.exception.EvaluationExceptionMessage.EVALUATION_NOT_EXIST;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {

    default EvaluationEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException((EVALUATION_NOT_EXIST)));
    }

    boolean existsByApplicantIdAndWriterId(Long applicantId, Long memberId);

    List<EvaluationEntity> findAllByApplicant(ApplicantEntity applicant);

}
