package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.ApplicantJpaRepository;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidIsolationLevelException;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationJpaRepository evaluationRepository;
    private final ApplicantJpaRepository applicantRepository;
    private final ManagerJpaRepository managerRepository;

    public EvaluationResponse createEvaluation(Long applicantId, Long managerId, EvaluationRequest request) {
        boolean evaluationExists = evaluationRepository.existsByApplicantIdAndWriterId(applicantId, managerId);

        if (evaluationExists) {
            throw new IllegalArgumentException("[Error] 이미 평가한 지원자입니다.");
        }

        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        ManagerEntity writer = managerRepository.findByIdOrThrow(managerId);

        EvaluationEntity evaluation = request.toEntity(applicant, writer);

        EvaluationEntity saved = evaluationRepository.save(evaluation);

        return EvaluationResponse.createEvaluationResponse(saved);
    }

    public EvaluationResponse getEvaluation(Long evaluationId) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);
        return EvaluationResponse.createEvaluationResponse(evaluation);
    }

    public EvaluationResponse updateEvaluation(Long evaluationId, Long managerId, EvaluationRequest request) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);

        if (request.comment() != null) {
            evaluation.setComment(request.comment());
        }

        if (request.pass() != null) {
            evaluation.setPass(request.pass());
        }

        EvaluationEntity updated = evaluationRepository.save(evaluation);

        return EvaluationResponse.createEvaluationResponse(updated);
    }

}
