package com.picksa.picksaserver.question.dto.response;

import com.picksa.picksaserver.global.domain.Part;

import java.time.LocalDateTime;

public record QuestionResponse(
        Long id,
        int sequence,
        boolean isDetermined,
        String content,
        Long tagId,
        String tagContent,
        Part part,
        Long writerId,
        String writerName,
        LocalDateTime createdAt
) {
}
