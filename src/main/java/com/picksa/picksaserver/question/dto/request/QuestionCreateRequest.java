package com.picksa.picksaserver.question.dto.request;

import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.TagEntity;

public record QuestionCreateRequest(String content, Long tagId) {

    public QuestionEntity toEntity(UserEntity writer, TagEntity tag) {
        return QuestionEntity.builder()
                .isDetermined(false)
                .content(this.content())
                .tag(tag)
                .writer(writer)
                .build();
    }

}
