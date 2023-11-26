package com.picksa.picksaserver.applicant.dto.response;

import java.util.List;

public record ApplicantAllResponse(
        int generation,
        int managerCount,
        List<ApplicantResponse> applicants
) {
    public static ApplicantAllResponse of(int generation,
                                          int managerCount,
                                          List<ApplicantResponse> applicants) {
        return new ApplicantAllResponse(generation, managerCount, applicants);
    }

}
