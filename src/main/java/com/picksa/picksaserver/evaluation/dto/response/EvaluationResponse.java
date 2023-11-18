package com.picksa.picksaserver.evaluation.dto.response;

public record EvaluationResponse (
    Long applicantId,
    Long managerId,
    boolean pass,
    String comment
)
{

}
