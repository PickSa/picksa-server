package com.picksa.picksaserver.question.service;

import com.picksa.picksaserver.global.auth.CustomUserDetails;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.QuestionOrderCondition;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
import com.picksa.picksaserver.question.dto.response.QuestionDeleteResponse;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;
import com.picksa.picksaserver.question.repository.QuestionRepository;
import com.picksa.picksaserver.question.repository.TagRepository;
import com.picksa.picksaserver.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.picksa.picksaserver.global.domain.Generation.getGenerationOfThisYear;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;

    public QuestionCreateResponse createQuestion(QuestionCreateRequest request) {
        UserEntity writer = getUser();

        Optional<TagEntity> optionalTag = tagRepository.findById(request.tagId());
        TagEntity tag = optionalTag.orElseThrow(() -> new EntityNotFoundException("[Error] 존재하지 않는 태그입니다."));

        QuestionEntity question = request.toEntity(writer, tag);
        QuestionEntity saved = questionRepository.save(question);
        return new QuestionCreateResponse(saved.getId(), saved.getContent(), saved.getTag().getId());
    }

    public List<QuestionDetermine> determineQuestions(List<QuestionDetermine> requests) {
        UserEntity writer = getUser();
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

    @Transactional
    public QuestionDeleteResponse deleteQuestion(Long questionId) {
        UserEntity writer = getUser();
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("[Error] 존재하지 않는 질문입니다. id: " + questionId));

        if (!question.getWriter().getId().equals(writer.getId())) {
            throw new AccessDeniedException("[Error] 질문 작성자만 삭제할 수 있습니다.");
        }

        question.deleteQuestion();
        return QuestionDeleteResponse.of(question.getId());
    }

    public List<QuestionResponse> getAllQuestionsByPart(Part partCondition, QuestionOrderCondition orderCondition) {
        int generation = getGenerationOfThisYear();
        return questionRepository.findAllQuestionsByPart(partCondition, orderCondition, generation);
    }

    public List<QuestionResponse> getDeterminedQuestions(Part partCondition) {
        int generation = getGenerationOfThisYear();
        return questionRepository.findDeterminedQuestionsByPart(partCondition, generation);
    }

    private UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUserEntity();
    }
}
