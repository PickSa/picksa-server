package com.picksa.picksaserver.question.service;

import com.picksa.picksaserver.manager.ManagerEntity;
import com.picksa.picksaserver.manager.ManagerJpaRepository;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
import com.picksa.picksaserver.question.repository.QuestionRepository;
import com.picksa.picksaserver.question.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<QuestionDetermine> determineQuestions(Long managerId, List<QuestionDetermine> requests) {
        ManagerEntity writer = managerRepository.findByIdOrThrow(managerId);
        List<QuestionEntity> questionsToUpdate = questionRepository.findAllById(
                requests.stream()
                        .map(request -> request.id())
                        .collect(Collectors.toList())
        );

        List<QuestionDetermine> responses = new ArrayList<>();
        for (QuestionDetermine request : requests) {
            QuestionEntity question = questionsToUpdate.stream()
                    .filter(q -> q.getId().equals(request.id()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("[Error] 존재하지 않는 질문입니다. id: " + request.id()));

            responses.add(question.updateIsDetermined(request.isDetermined()));
        }

        questionRepository.saveAll(questionsToUpdate);
        return responses;
    }
}
