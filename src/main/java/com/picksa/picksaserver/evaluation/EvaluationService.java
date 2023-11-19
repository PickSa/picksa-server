package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.ApplicantJpaRepository;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationJpaRepository evaluationRepository;
    private final ApplicantJpaRepository applicantRepository;
    private final ManagerJpaRepository managerRepository;

    public EvaluationResponse createEvaluation(Long applicantId, Long managerId, EvaluationRequest request) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        ManagerEntity writer = managerRepository.findByIdOrThrow(managerId);

        EvaluationEntity evaluation = EvaluationEntity.builder()
            .pass(request.pass())
            .comment(request.comment())
            .applicant(applicant)
            .writer(writer)
            .build();

        EvaluationEntity saved = evaluationRepository.save(evaluation);

        return new EvaluationResponse(
            saved.getApplicant().getId(),
            saved.getWriter().getId(),
            saved.isPass(),
            saved.getComment()
            );
    }

}
