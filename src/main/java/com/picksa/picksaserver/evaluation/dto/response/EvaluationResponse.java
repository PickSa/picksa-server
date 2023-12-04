package com.picksa.picksaserver.evaluation.dto.response;

import com.picksa.picksaserver.evaluation.EvaluationEntity;

public record EvaluationResponse(
    Long evaluationId,
    Long managerId,
    Long applicantId,
    String name,
    Boolean pass,
    String comment
) {
    public static EvaluationResponse of(EvaluationEntity evaluation) {
        return new EvaluationResponse(
            evaluation.getId(),
            evaluation.getWriter().getId(),
            evaluation.getApplicant().getId(),
            evaluation.getWriter().getName(),
            evaluation.isPass(),
            evaluation.getComment()
        );
    }

}