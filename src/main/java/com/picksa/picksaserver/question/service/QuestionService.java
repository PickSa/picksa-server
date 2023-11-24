package com.picksa.picksaserver.question.service;

import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.request.QuestionRequest;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;
import com.picksa.picksaserver.question.repository.QuestionRepository;
import com.picksa.picksaserver.question.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ManagerJpaRepository managerRepository;
    private final TagRepository tagRepository;

    public QuestionResponse createQuestion(Long managerId, QuestionRequest request) {
        ManagerEntity writer = managerRepository.findByIdOrThrow(managerId);

        Optional<TagEntity> optionalTag = tagRepository.findById(request.tagId());
        TagEntity tag = optionalTag.orElseThrow();

        QuestionEntity question = request.toEntity(writer, tag);
        QuestionEntity saved = questionRepository.save(question);
        return new QuestionResponse(saved.getId(), saved.getContent(), saved.getTag().getId());
    }
}
