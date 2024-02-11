package com.picksa.picksaserver.applicant.dto.request;

import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public record InterviewScheduleCreateRequest(
        LocalDate date,
        LocalTime startAt,
        LocalTime finishAt
) {

    public InterviewScheduleEntity toEntity() {
        return InterviewScheduleEntity.builder()
                .date(this.date)
                .startAt(this.startAt)
                .finishAt(this.finishAt)
                .build();
    }

}
