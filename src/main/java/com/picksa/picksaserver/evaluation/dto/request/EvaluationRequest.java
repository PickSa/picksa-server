package com.picksa.picksaserver.evaluation.dto.request;

public record EvaluationRequest (
    boolean pass,
    String comment
) {

}
