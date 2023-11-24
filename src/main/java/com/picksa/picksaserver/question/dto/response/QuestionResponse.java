package com.picksa.picksaserver.question.dto.response;

public record QuestionResponse(
        Long questionId,
        String content,
        Long tagId
) {
}
