package com.picksa.picksaserver.question.dto.response;

public record QuestionCreateResponse(
        Long questionId,
        String content,
        Long tagId
) {
}
