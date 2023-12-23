package com.picksa.picksaserver.applicant.dto.response;

import com.picksa.picksaserver.applicant.Result;
import com.picksa.picksaserver.global.domain.Part;

public record ApplicantResponse(
        Long applicantId,
        Part part,
        String name,
        String studentId,
        String phone,
        int score,
        boolean isEvaluated,
        Result result
) {

}
