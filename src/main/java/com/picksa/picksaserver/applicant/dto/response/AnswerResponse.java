package com.picksa.picksaserver.applicant.dto.response;

import com.picksa.picksaserver.applicant.AnswerEntity;
import lombok.Builder;

@Builder
public record AnswerResponse(
        Long answerId,
        int sequence,
        String tag,
        String question,
        String answer
) {

    public static AnswerResponse from(AnswerEntity answer) {
        return AnswerResponse.builder()
                .answerId(answer.getId())
                .sequence(answer.getQuestion().getSequence())
                .tag(answer.getQuestion().getTag().getContent())
                .question(answer.getQuestion().getContent())
                .answer(answer.getContent())
                .build();
    }

}
