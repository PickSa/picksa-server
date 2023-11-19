package com.picksa.picksaserver.evaluation.dto.response;

public record EvaluationResponse (
    Long evaluationId,
    boolean pass,
    String comment
)
{

}
