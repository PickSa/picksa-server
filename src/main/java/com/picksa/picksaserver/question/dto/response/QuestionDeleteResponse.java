package com.picksa.picksaserver.question.dto.response;

public record QuestionDeleteResponse(Long deletedId) {
    public static QuestionDeleteResponse of(Long deletedId) {
        return new QuestionDeleteResponse(deletedId);
    }
}
