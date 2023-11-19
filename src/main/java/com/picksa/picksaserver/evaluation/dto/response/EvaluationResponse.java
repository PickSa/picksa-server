package com.picksa.picksaserver.evaluation.dto.response;

public record EvaluationResponse (
    Long evaluationId,
    Boolean pass,
    String comment
)
{

}
