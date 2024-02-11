package com.picksa.picksaserver.evaluation.service;

import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.applicant.repository.ApplicantRepository;
import com.picksa.picksaserver.evaluation.EvaluationEntity;
import com.picksa.picksaserver.evaluation.EvaluationRepository;
import com.picksa.picksaserver.evaluation.dto.request.DecideRequest;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.DecideResponse;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.evaluation.dto.response.FinalEvaluationResponse;
import com.picksa.picksaserver.global.auth.CustomUserDetails;
import com.picksa.picksaserver.global.domain.Generation;
import com.picksa.picksaserver.user.Position;
import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.picksa.picksaserver.evaluation.exception.EvaluationExceptionMessage.ALREADY_EVALUATED;
import static com.picksa.picksaserver.evaluation.exception.EvaluationExceptionMessage.UPDATE_NOT_PERMITTED;
import static com.picksa.picksaserver.evaluation.exception.EvaluationExceptionMessage.USER_NOT_PART_LEADER;
import static com.picksa.picksaserver.evaluation.exception.EvaluationExceptionMessage.USER_PART_MISMATCHED;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final ApplicantRepository applicantRepository;
    private final UserRepository userRepository;

    @Transactional
    public EvaluationResponse createEvaluation(Long applicantId, EvaluationRequest request) {
        UserEntity writer = getUser();

        boolean evaluationExists = evaluationRepository.existsByApplicantIdAndWriterId(applicantId, writer.getId());

        if (evaluationExists) {
            throw new IllegalArgumentException(ALREADY_EVALUATED);
        }

        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);

        if (request.pass()) {
            applicant.upScore();
        }

        EvaluationEntity evaluation = request.toEntity(applicant, writer);
        EvaluationEntity saved = evaluationRepository.save(evaluation);

        int currentEvaluationCount = evaluationRepository.findAllByApplicant(applicant).size();
        if (isDone(currentEvaluationCount)) {
            applicant.evaluationDone();
        }

        return EvaluationResponse.of(saved);
    }

    private boolean isDone(int evaluationCount) {
        int generation = Generation.getGenerationOfThisYear();
        return evaluationCount == userRepository.countByGeneration(generation);
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
        Long userId = getUser().getId();
        if (!Objects.equals(evaluation.getWriter().getId(), userId)) {
            throw new AccessDeniedException(UPDATE_NOT_PERMITTED);
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
        Long writerId = getUser().getId();

        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        List<EvaluationEntity> evaluations = evaluationRepository.findAllByApplicant(applicant);

        boolean hasMatchingWriterId = evaluations.stream()
            .anyMatch(evaluation -> evaluation.getWriter().getId().equals(writerId));

        if (hasMatchingWriterId) {
            evaluations.sort((e1, e2) -> {
                if (e1.getWriter().getId().equals(writerId)) {
                    return -1; // 현재 객체(e1)를 이전 객체(e2)보다 앞으로 정렬
                } else if (e2.getWriter().getId().equals(writerId)) {
                    return 1; // 객체(e2)를 이전 객체(e1)보다 뒤로 정렬
                } else {
                    return 0; // 순서를 변경하지 않음
                }
            });
        }

        return evaluations.stream()
            .map(EvaluationResponse::of)
            .collect(Collectors.toList());
    }

    private void isCorrectPart(ApplicantEntity applicant, UserEntity user) {
        if (user.getPart() != applicant.getPart())
            throw new AccessDeniedException(USER_PART_MISMATCHED);
    }

    private void isPartLeader(UserEntity user) {
        if (!user.getPosition().equals(Position.PART_LEADER)) {
            throw new AccessDeniedException(USER_NOT_PART_LEADER);
        }
    }

    @Transactional
    public DecideResponse decideEvaluation(Long applicantId, DecideRequest decideRequest) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        UserEntity user = getUser();
        isCorrectPart(applicant, user);
        isPartLeader(user);
        applicant.decideResult(decideRequest.result());
        return DecideResponse.from(applicantId, applicant.getResult().getResultName());
    }

    public FinalEvaluationResponse getFinalResult(Long applicantId) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        return FinalEvaluationResponse.of(applicant);
    }

    private UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUserEntity();
    }

}

