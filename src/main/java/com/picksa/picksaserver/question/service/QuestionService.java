package com.picksa.picksaserver.question.service;

import com.picksa.picksaserver.user.UserEntity;
import com.picksa.picksaserver.user.UserJpaRepository;
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
    private final UserJpaRepository userRepository;
    private final TagRepository tagRepository;

    public QuestionCreateResponse createQuestion(Long userId, QuestionCreateRequest request) {
        UserEntity writer = userRepository.findByIdOrThrow(userId);

        Optional<TagEntity> optionalTag = tagRepository.findById(request.tagId());
        TagEntity tag = optionalTag.orElseThrow(() -> new EntityNotFoundException("[Error] 존재하지 않는 태그입니다."));

        QuestionEntity question = request.toEntity(writer, tag);
        QuestionEntity saved = questionRepository.save(question);
        return new QuestionCreateResponse(saved.getId(), saved.getContent(), saved.getTag().getId());
    }

    public List<QuestionDetermine> determineQuestions(Long userId, List<QuestionDetermine> requests) {
        UserEntity writer = userRepository.findByIdOrThrow(userId);
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
