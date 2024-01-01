package com.picksa.picksaserver.applicant.dto.response;

import java.util.List;

public record ApplicantScheduleResponses(
        List<InterviewScheduleResponse> schedules,
        List<ApplicantScheduleResponse> applicants
) {
    public static ApplicantScheduleResponses of(List<InterviewScheduleResponse> schedules, List<ApplicantScheduleResponse> applicants) {
        return new ApplicantScheduleResponses(schedules, applicants);
    }
}
