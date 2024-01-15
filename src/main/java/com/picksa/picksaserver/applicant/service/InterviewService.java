package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.InterviewScheduleEntity;
import com.picksa.picksaserver.applicant.dto.request.InterviewScheduleCreateRequest;
import com.picksa.picksaserver.applicant.repository.InterviewScheduleRepository;
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

}
