package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.evaluation.EvaluationEntity;

public record EvaluationResponse(
        Long evaluationId,
        Long managerId,
        String name,
        Boolean pass,
        String comment
) {
    public static EvaluationResponse of(EvaluationEntity evaluation) {
        return new EvaluationResponse(
            evaluation.getId(),
            evaluation.getWriter().getId(),
            evaluation.getWriter().getName(),
            evaluation.getPass(),
            evaluation.getComment()
        );
    }

}
