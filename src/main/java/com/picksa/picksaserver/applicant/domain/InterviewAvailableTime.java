package com.picksa.picksaserver.applicant.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Embeddable
public class InterviewAvailableTime {

    private DayOfWeek dayOfWeek;
    private LocalTime time;

}
