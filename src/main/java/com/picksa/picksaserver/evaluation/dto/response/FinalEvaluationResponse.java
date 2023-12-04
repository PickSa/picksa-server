package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.applicant.Result;

public record FinalEvaluationResponse (
    int score,
    boolean isEvaluated,
    Result result
){

    public static FinalEvaluationResponse of(ApplicantEntity applicant) {
        return new FinalEvaluationResponse(
            applicant.getScore(),
            applicant.isEvaluated(),
            applicant.getResult()
        );
    }

}
