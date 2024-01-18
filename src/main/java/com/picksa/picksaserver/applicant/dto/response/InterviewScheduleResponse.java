package com.picksa.picksaserver.applicant.dto.response;

import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record InterviewScheduleResponse(
        LocalDate date,
        LocalTime startAt,
        LocalTime finishAt
) {

    public static InterviewScheduleResponse from(InterviewScheduleEntity interviewSchedule) {
        return InterviewScheduleResponse.builder()
                .date(interviewSchedule.getDate())
                .startAt(interviewSchedule.getStartAt())
                .finishAt(interviewSchedule.getFinishAt())
                .build();
    }

}
