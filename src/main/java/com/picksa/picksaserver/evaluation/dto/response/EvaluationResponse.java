package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.evaluation.EvaluationEntity;

public record EvaluationResponse(
        Long evaluationId,
        boolean pass,
        String comment
) {
    public static EvaluationResponse of(EvaluationEntity evaluation) {
        return new EvaluationResponse(
            evaluation.getId(),
                evaluation.isPass(),
            evaluation.getComment()
        );
    }

}
