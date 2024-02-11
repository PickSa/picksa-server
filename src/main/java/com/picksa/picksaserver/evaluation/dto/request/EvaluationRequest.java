package com.picksa.picksaserver.evaluation.dto.request;

import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.evaluation.EvaluationEntity;
import com.picksa.picksaserver.user.UserEntity;

public record EvaluationRequest(
        Boolean pass,
        String comment
) {

    public EvaluationEntity toEntity(ApplicantEntity applicant, UserEntity user) {
        return EvaluationEntity.builder()
                .pass(this.pass())
                .comment(this.comment())
                .applicant(applicant)
                .writer(user)
                .build();
    }

}
