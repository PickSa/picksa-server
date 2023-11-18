package com.picksa.picksaserver.evaluation.dto.request;

public record EvaluationRequest (
    Long applicantId,
    String comment
) {

}
