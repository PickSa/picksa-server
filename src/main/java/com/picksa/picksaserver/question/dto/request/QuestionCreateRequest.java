package com.picksa.picksaserver.question.dto.request;

import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.user.UserEntity;

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
