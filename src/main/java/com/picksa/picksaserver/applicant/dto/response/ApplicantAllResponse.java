package com.picksa.picksaserver.applicant.dto.response;

import java.util.List;

public record ApplicantAllResponse(
        int generation,
        int userCount,
        List<ApplicantResponse> applicants
) {
    public static ApplicantAllResponse of(int generation,
                                          int userCount,
                                          List<ApplicantResponse> applicants) {
        return new ApplicantAllResponse(generation, userCount, applicants);
    }

}
