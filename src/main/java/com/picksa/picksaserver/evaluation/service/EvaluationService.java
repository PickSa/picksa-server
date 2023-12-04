package com.picksa.picksaserver.evaluation.service;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.ApplicantJpaRepository;
import com.picksa.picksaserver.evaluation.EvaluationEntity;
import com.picksa.picksaserver.evaluation.EvaluationJpaRepository;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

        if (request.pass()) {
            applicant.upScore();
        }

        EvaluationEntity evaluation = request.toEntity(applicant, writer);
        EvaluationEntity saved = evaluationRepository.save(evaluation);

        return EvaluationResponse.of(saved);
    }

    public EvaluationResponse getEvaluation(Long evaluationId) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);
        return EvaluationResponse.of(evaluation);
    }

    @Transactional
    public EvaluationResponse updateEvaluation(Long evaluationId, EvaluationRequest request) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);
        UserEntity user = getUser();
        if (!Objects.equals(evaluation.getWriter().getId(), user.getId())) {
            throw new IllegalArgumentException("본인의 평가만 수정이 가능합니다.");
        }

        manageScore(evaluation, request);

        if (request.comment() != null) {
            evaluation.updateComment(request.comment());
        }

        if (request.pass() != null) {
            evaluation.updatePass(request.pass());
        }

        return EvaluationResponse.of(evaluation);
    }

    public List<EvaluationResponse> getEvaluationByApplicant(Long applicantId) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        return evaluationRepository.findAllByApplicant(applicant).stream()
                .map(EvaluationResponse::of).toList();
    }

    private void manageScore(EvaluationEntity evaluation, EvaluationRequest request) {
        if (evaluation.isPass() != request.pass()) {
            if (request.pass()) {
                evaluation.getApplicant().upScore();
            }
            if (!request.pass()) {
                evaluation.getApplicant().downScore();
            }
        }
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

    private UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.isAuthenticated() = " + authentication.isAuthenticated());
        System.out.println("authentication.getPrincipal(). = " + authentication.getPrincipal().toString());
        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return userRepository.findByIdOrThrow(userId);
    }

}
