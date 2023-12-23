package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.AnswerEntity;
import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantAllResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantDetailResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.applicant.repository.AnswerRepository;
import com.picksa.picksaserver.applicant.repository.ApplicantRepository;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static com.picksa.picksaserver.global.domain.Generation.getGenerationOfThisYear;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final AnswerRepository answerRepository;
    private final UserJpaRepository userRepository;

    @Transactional(readOnly = true)
    public ApplicantAllResponse getAllApplicants(OrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        int userCount = getuserCount(generation);
        List<ApplicantResponse> applicants = applicantRepository.findAllApplicants(orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                userCount,
                applicants
        );
    }

    @Transactional(readOnly = true)
    public ApplicantAllResponse getApplicantsByPart(Part part, OrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        int userCount = getuserCount(generation);
        List<ApplicantResponse> applicants = applicantRepository.findApplicantsByPart(part, orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                userCount,
                applicants
        );
    }

    @Transactional(readOnly = true)
    public ApplicantDetailResponse getApplicantDetail(Long applicantId) {
        ApplicantEntity applicant = applicantRepository.findByIdOrThrow(applicantId);
        List<AnswerEntity> answers = answerRepository.findByApplicantId(applicantId).stream()
                .sorted(Comparator.comparingInt(answer -> answer.getQuestion().getSequence()))
                .toList();
        return ApplicantDetailResponse.of(applicant, answers);
    }

    private int getuserCount(int generation) {
        return userRepository.countByGeneration(generation);
    }

}
