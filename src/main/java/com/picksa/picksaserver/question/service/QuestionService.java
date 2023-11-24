package com.picksa.picksaserver.question.service;

import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
import com.picksa.picksaserver.question.repository.QuestionRepository;
import com.picksa.picksaserver.question.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ManagerJpaRepository managerRepository;
    private final TagRepository tagRepository;

    public QuestionCreateResponse createQuestion(Long managerId, QuestionCreateRequest request) {
        ManagerEntity writer = managerRepository.findByIdOrThrow(managerId);

        Optional<TagEntity> optionalTag = tagRepository.findById(request.tagId());
        TagEntity tag = optionalTag.orElseThrow(() -> new EntityNotFoundException("[Error] 존재하지 않는 태그입니다."));

        QuestionEntity question = request.toEntity(writer, tag);
        QuestionEntity saved = questionRepository.save(question);
        return new QuestionCreateResponse(saved.getId(), saved.getContent(), saved.getTag().getId());
    }

}
