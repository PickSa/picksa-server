package com.picksa.picksaserver.applicant.service;

import com.picksa.picksaserver.applicant.InterviewScheduleEntity;
import com.picksa.picksaserver.applicant.dto.request.InterviewScheduleCreateRequest;
import com.picksa.picksaserver.applicant.repository.InterviewScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewScheduleRepository interviewScheduleRepository;

    @Transactional
    public Long create(InterviewScheduleCreateRequest request) {
        InterviewScheduleEntity interviewSchedule = request.toEntity();
        InterviewScheduleEntity saved = interviewScheduleRepository.save(interviewSchedule);
        return saved.getId();
    }

}
