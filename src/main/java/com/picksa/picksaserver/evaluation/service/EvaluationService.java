package com.picksa.picksaserver.evaluation.service;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.ApplicantJpaRepository;
import com.picksa.picksaserver.evaluation.EvaluationEntity;
import com.picksa.picksaserver.evaluation.EvaluationJpaRepository;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import com.picksa.picksaserver.manager.Position;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationJpaRepository evaluationRepository;
    private final ApplicantJpaRepository applicantRepository;
    private final ManagerJpaRepository managerRepository;

    @Transactional
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

    @Transactional
    public EvaluationResponse updateEvaluation(Long evaluationId, Long managerId, EvaluationRequest request) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);

        if (request.comment() != null) {
            evaluation.updateComment(request.comment());
        }

        if (request.pass() != null) {
            evaluation.updatePass(request.pass());
        }

        return EvaluationResponse.createEvaluationResponse(evaluation);
    }

    public List<EvaluationResponse> getByApplicant(Long applicantId) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        return evaluationRepository.findAllByApplicant(applicant).stream()
            .map(EvaluationResponse::createEvaluationResponse).toList();
    }

    private void isCorrectPart(ApplicantEntity applicant, ManagerEntity manager) {
        if (manager.getPart() != applicant.getPart())
            throw new IllegalArgumentException("[ERROR] 해당 파트의 운영진이 아닙니다.");
    }

    private void isPartLeader(ManagerEntity manager) {
        if (!manager.getPosition().equals(Position.PART_LEADER)) {
            throw new IllegalArgumentException("[ERROR] 파트장이 아닙니다.");
        }
    }


    public void decideEvaluation(Long applicantId, Long managerId) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        ManagerEntity manager = managerRepository.findByIdOrThrow(managerId);
        isCorrectPart(applicant, manager);
        isPartLeader(manager);
        ap
    }

}
