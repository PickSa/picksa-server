package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.applicant.domain.Result;

public record FinalEvaluationResponse (
    Long applicantId,
    int score,
    boolean isEvaluated,
    Result result
){

    public static FinalEvaluationResponse of(ApplicantEntity applicant) {
        return new FinalEvaluationResponse(
            applicant.getId(),
            applicant.getScore(),
            applicant.isEvaluated(),
            applicant.getResult()
        );
    }

}
