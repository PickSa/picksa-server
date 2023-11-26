package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.evaluation.EvaluationEntity;

public record EvaluationResponse(
        Long evaluationId,
        Long managerId,
        Boolean pass,
        String comment
) {
    public static EvaluationResponse of(EvaluationEntity evaluation) {
        return new EvaluationResponse(
            evaluation.getWriter().getId(),
            evaluation.getId(),
            evaluation.getPass(),
            evaluation.getComment()
        );
    }

}
