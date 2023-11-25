package com.picksa.picksaserver.question.dto.response;

public record QuestionResponse(
        Long id,
        String content,
        Long tagId,
        String tagContent,
        Long writerId
) {
}
