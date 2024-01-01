package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionOrderCondition;
import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.request.QuestionUpdateSequenceRequest;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
import com.picksa.picksaserver.question.dto.response.QuestionDeleteResponse;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;
import com.picksa.picksaserver.question.dto.response.QuestionUpdateSequenceResponse;
import com.picksa.picksaserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<QuestionCreateResponse> create (@RequestBody QuestionCreateRequest request) {
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
    public ResponseEntity<List<QuestionDetermine>> updateFinal (@RequestBody List<QuestionDetermine> requests) {
        List<QuestionDetermine> determinedQuestions = questionService.determineQuestions(requests);
        return ResponseEntity.ok(determinedQuestions);
    }

    @GetMapping("/final")
    public ResponseEntity<List<QuestionResponse>> getFinalQuestions (@RequestParam String part) {
        Part partCondition = Optional.of(part)
                .filter(StringUtils::hasText)
                .map(Part::from)
                .orElseThrow(() -> new IllegalArgumentException("[Error] part 파라미터가 잘못되었습니다."));
        List<QuestionResponse> questions = questionService.getDeterminedQuestions(partCondition);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(
    		@PathVariable("questionId") Long questionId
    		) {
        QuestionDeleteResponse response = questionService.deleteQuestion(questionId);
    	return ResponseEntity.ok(response);
    }

    @PatchMapping("/reorder")
    public ResponseEntity<List<QuestionUpdateSequenceResponse>> updateOrder(
            @RequestBody List<QuestionUpdateSequenceRequest> requests
    ) {
        List<QuestionUpdateSequenceResponse> response = questionService.updateOrder(requests);
        return ResponseEntity.ok(response);
    }

}
