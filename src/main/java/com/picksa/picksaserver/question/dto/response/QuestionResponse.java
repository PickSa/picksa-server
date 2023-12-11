package com.picksa.picksaserver.question.dto.response;

import java.time.LocalDateTime;

public record QuestionResponse(
        Long id,
        int sequence,
        boolean isDetermined,
        String content,
        Long tagId,
        String tagContent,
        Long writerId,
        LocalDateTime createdAt
) {
}
