package com.picksa.picksaserver.evaluation.service;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.repository.ApplicantJpaRepository;
import com.picksa.picksaserver.evaluation.EvaluationEntity;
import com.picksa.picksaserver.evaluation.EvaluationJpaRepository;
import com.picksa.picksaserver.evaluation.dto.request.DecideRequest;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.DecideResponse;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.evaluation.dto.response.FinalEvaluationResponse;
import com.picksa.picksaserver.user.Position;
import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationJpaRepository evaluationRepository;
    private final ApplicantJpaRepository applicantRepository;
    private final UserJpaRepository userRepository;

    @Transactional
    public EvaluationResponse createEvaluation(Long applicantId, EvaluationRequest request) {
        Long userId = getUserId();
        boolean evaluationExists = evaluationRepository.existsByApplicantIdAndWriterId(applicantId, userId);

        if (evaluationExists) {
            throw new IllegalArgumentException("[Error] 이미 평가한 지원자입니다.");
        }

        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);

        if (request.pass()) {
            applicant.upScore();
        }

        UserEntity writer = userRepository.findByIdOrThrow(userId);
        EvaluationEntity evaluation = request.toEntity(applicant, writer);
        EvaluationEntity saved = evaluationRepository.save(evaluation);

        int currentEvaluationCount = evaluationRepository.findAllByApplicant(applicant).size();
        if (isDone(currentEvaluationCount)) {
            applicant.evaluationDone();
        }

        return EvaluationResponse.of(saved);
    }

    private boolean isDone(int evaluationCount) {
        System.out.println(userRepository.count());
        return evaluationCount == userRepository.count();
    }

    public EvaluationResponse getEvaluation(Long evaluationId) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);
        return EvaluationResponse.of(evaluation);
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

    @Transactional
    public EvaluationResponse updateEvaluation(Long evaluationId, EvaluationRequest request) {
        EvaluationEntity evaluation = evaluationRepository.findByIdOrThrow(evaluationId);
        Long userId = getUserId();
        if (!Objects.equals(evaluation.getWriter().getId(), userId)) {
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

    private void isCorrectPart(ApplicantEntity applicant, UserEntity user) {
        if (user.getPart() != applicant.getPart())
            throw new IllegalArgumentException("[ERROR] 해당 파트의 운영진이 아닙니다.");
    }

    private void isPartLeader(UserEntity user) {
        if (!user.getPosition().equals(Position.PART_LEADER)) {
            throw new IllegalArgumentException("[ERROR] 파트장이 아닙니다.");
        }
    }

    @Transactional
    public DecideResponse decideEvaluation(Long applicantId, DecideRequest decideRequest) {
        Long userId = getUserId();
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        UserEntity user = userRepository.findByIdOrThrow(userId);
        isCorrectPart(applicant, user);
        isPartLeader(user);
        applicant.decideResult(decideRequest.result());
        return DecideResponse.from(applicantId, applicant.getResult().getResultName());
    }

    public FinalEvaluationResponse getFinalResult(Long applicantId) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        return FinalEvaluationResponse.of(applicant);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Long.valueOf(authentication.getPrincipal().toString());
    }

}
