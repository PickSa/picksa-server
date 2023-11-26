package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.controller.ApplicantController;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.manager.ManagerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationJpaRepository extends JpaRepository <EvaluationEntity, Long> {

    default EvaluationEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
            () -> new IllegalArgumentException(("[Error] 해당 평가가 존재하지 않습니다.")));
    }

    boolean existsByApplicantIdAndWriterId(Long applicantId, Long memberId);

    List<EvaluationEntity> findAllByApplicant(ApplicantEntity applicant);

}
