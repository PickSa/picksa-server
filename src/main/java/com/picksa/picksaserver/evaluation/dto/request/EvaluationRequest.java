package com.picksa.picksaserver.evaluation.dto.request;

public record EvaluationRequest(
    Boolean pass,
    String comment
) {

}
