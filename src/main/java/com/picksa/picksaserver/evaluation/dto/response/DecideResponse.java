package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.applicant.Result;

public record DecideResponse(
    Long applicantId,
    Result result
) {
    public static DecideResponse from(Long applicantId, Result result) {
        return new DecideResponse(
            applicantId,
            result
        );
    }

}
