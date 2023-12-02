package com.picksa.picksaserver.evaluation.dto.request;

import com.picksa.picksaserver.applicant.ApplicantEntity;
import com.picksa.picksaserver.evaluation.EvaluationEntity;
import com.picksa.picksaserver.manager.ManagerEntity;

public record EvaluationRequest(
        Boolean pass,
        String comment
) {
    public EvaluationEntity toEntity(ApplicantEntity applicant, ManagerEntity manager) {
        return EvaluationEntity.builder()
                .pass(this.pass())
                .comment(this.comment())
                .applicant(applicant)
                .writer(manager)
                .build();
    }

}
