package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionOrderCondition;
import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
import com.picksa.picksaserver.question.dto.response.QuestionDeleteResponse;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;
import com.picksa.picksaserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<QuestionCreateResponse> create (
            @RequestBody QuestionCreateRequest request
    ) {
        QuestionCreateResponse response = questionService.createQuestion(request);
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<QuestionResponse>> getAllQuestionsByPart(
            @RequestParam String part,
            @RequestParam String order
    ) {
        Part partCondition = Optional.of(part)
                .filter(StringUtils::hasText)
                .map(Part::from)
                .orElseThrow(() -> new IllegalArgumentException("[Error] part 파라미터가 잘못되었습니다."));

        QuestionOrderCondition orderCondition = Optional.of(order)
                .filter(StringUtils::hasText)
                .map(QuestionOrderCondition::from)
                .orElseThrow(() -> new IllegalArgumentException("[Error] order 파라미터가 잘못되었습니다."));

        List<QuestionResponse> questions = questionService.getAllQuestionsByPart(partCondition, orderCondition);
        return ResponseEntity.ok(questions);
    }

    @PatchMapping("/final")
    public ResponseEntity<List<QuestionDetermine>> updateFinal (
            @RequestBody List<QuestionDetermine> requests
            ) {
        List<QuestionDetermine> determinedQuestions = questionService.determineQuestions(requests);
        return ResponseEntity.ok(determinedQuestions);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(
    		@PathVariable("questionId") Long questionId
    		) {
        QuestionDeleteResponse response = questionService.deleteQuestion(questionId);
    	return ResponseEntity.ok(response);
    }

}
