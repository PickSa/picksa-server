package com.picksa.picksaserver.question.dto.request;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.repository.TagRepository;

public record QuestionRequest(String content, Long tagId) {

    public QuestionEntity toEntity(ManagerEntity writer, TagEntity tag) {
        return QuestionEntity.builder()
                .isDetermined(false)
                .content(this.content())
                .tag(tag)
                .writer(writer)
                .build();
    }
}
