package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.evaluation.EvaluationEntity;

public record EvaluationResponse(
        Long evaluationId,
        Boolean pass,
        String comment
) {
    public static EvaluationResponse createEvaluationResponse(EvaluationEntity evaluation) {
        return new EvaluationResponse(
                evaluation.getId(),
                evaluation.getPass(),
                evaluation.getComment()
        );
    }

}
