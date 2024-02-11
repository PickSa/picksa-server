package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;
import com.picksa.picksaserver.applicant.dto.request.InterviewScheduleCreateRequest;
import com.picksa.picksaserver.applicant.dto.response.InterviewScheduleResponse;
import com.picksa.picksaserver.applicant.repository.InterviewScheduleRepository;
import com.picksa.picksaserver.global.domain.Generation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewScheduleRepository interviewScheduleRepository;

    @Transactional
    public void create(List<InterviewScheduleCreateRequest> request) {
        List<InterviewScheduleEntity> interviewSchedules = request.stream()
                .map(InterviewScheduleCreateRequest::toEntity)
                .toList();
        interviewScheduleRepository.saveAll(interviewSchedules);
    }

    @Transactional
    public List<InterviewScheduleResponse> getInterviewSchedulesOfThisYear() {
        int generation = Generation.getGenerationOfThisYear();
        List<InterviewScheduleEntity> interviewSchedules = interviewScheduleRepository.findByGenerationOrderByDate(generation);
        List<InterviewScheduleResponse> response = interviewSchedules.stream().map(InterviewScheduleResponse::from).toList();

        return response;
    }

    @Transactional
    public void update(List<InterviewScheduleCreateRequest> request) {
        int generation = Generation.getGenerationOfThisYear();
        interviewScheduleRepository.deleteAllByGeneration(generation);

        List<InterviewScheduleEntity> interviewSchedules = request.stream()
                .map(InterviewScheduleCreateRequest::toEntity)
                .toList();
        interviewScheduleRepository.saveAll(interviewSchedules);
    }

}
