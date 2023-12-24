package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static com.picksa.picksaserver.evaluation.exception.EvaluationExceptionMessage.EVALUATION_NOT_EXIST;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {

    default EvaluationEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new IllegalArgumentException(("[Error] 해당 평가가 존재하지 않습니다.")));
    }

    boolean existsByApplicantIdAndWriterId(Long applicantId, Long memberId);

    List<EvaluationEntity> findAllByApplicant(ApplicantEntity applicant);

}
