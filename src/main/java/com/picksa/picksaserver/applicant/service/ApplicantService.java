package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.domain.AnswerEntity;
import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;
import com.picksa.picksaserver.applicant.domain.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantAllResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantDetailResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantScheduleResponse;
import com.picksa.picksaserver.applicant.dto.response.ApplicantScheduleResponses;
import com.picksa.picksaserver.applicant.dto.response.InterviewScheduleResponse;
import com.picksa.picksaserver.applicant.repository.AnswerRepository;
import com.picksa.picksaserver.applicant.repository.ApplicantRepository;
import com.picksa.picksaserver.applicant.repository.InterviewScheduleRepository;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final InterviewScheduleRepository interviewScheduleRepository;

    @Transactional(readOnly = true)
    public ApplicantAllResponse getAllApplicants() {
        int generation = getGenerationOfThisYear();
        int userCount = getUserCount(generation);
        List<ApplicantResponse> applicants = applicantRepository.findAllApplicants(generation);
        return ApplicantAllResponse.of(
                generation,
                userCount,
                applicants
        );
    }

    @Transactional(readOnly = true)
    public ApplicantAllResponse getAllApplicantsByOrderCondition(OrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        int userCount = getUserCount(generation);
        List<ApplicantResponse> applicants = applicantRepository.findAllApplicantsByOrderCondition(orderCondition, generation);
        return ApplicantAllResponse.of(
                generation,
                userCount,
                applicants
        );
    }

    @Transactional(readOnly = true)
    public ApplicantAllResponse getApplicantsByPart(Part part, OrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        int userCount = getUserCount(generation);
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

    @Transactional(readOnly = true)
    public ApplicantScheduleResponses getApplicantsSchedules() {
        int generation = getGenerationOfThisYear();
        List<InterviewScheduleEntity> interviewSchedules = interviewScheduleRepository.findByGenerationOrderByDate(generation);
        List<InterviewScheduleResponse> schedules = interviewSchedules.stream().map(InterviewScheduleResponse::from).toList();
        List<ApplicantScheduleResponse> applicants = applicantRepository.findApplicantSchedules(generation);

        return ApplicantScheduleResponses.of(schedules, applicants);

    }

    private int getUserCount(int generation) {
        return userRepository.countByGeneration(generation);
    }

}
