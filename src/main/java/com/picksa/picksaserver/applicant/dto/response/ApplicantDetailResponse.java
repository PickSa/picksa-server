package com.picksa.picksaserver.applicant.dto.response;

import com.picksa.picksaserver.applicant.domain.AnswerEntity;
import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.applicant.domain.Result;
import lombok.Builder;

import java.util.List;

@Builder
public record ApplicantDetailResponse(
        Long applicantId,
        String name,
        String studentId,
        String major,
        String multimajor,
        String gender,
        String semester,
        String email,
        String phone,
        String portfolio,
        String part,
        Result result,
        boolean isEvaluated,
        int score,
        List<AnswerResponse> answers
) {

    public static ApplicantDetailResponse of(ApplicantEntity applicant, List<AnswerEntity> answers) {
        List<AnswerResponse> answerResponses = answers.stream().map(AnswerResponse::from).toList();
        return ApplicantDetailResponse.builder()
                .applicantId(applicant.getId())
                .name(applicant.getName())
                .studentId(applicant.getStudentId())
                .major(applicant.getMajor())
                .multimajor(applicant.getMultiMajor())
                .gender(applicant.getGender())
                .semester(applicant.getSemester())
                .email(applicant.getEmail())
                .phone(applicant.getPhone())
                .portfolio(applicant.getPortfolio())
                .part(applicant.getPart().getPartName())
                .result(applicant.getResult())
                .isEvaluated(applicant.isEvaluated())
                .score(applicant.getScore())
                .answers(answerResponses)
                .build();
    }

}
