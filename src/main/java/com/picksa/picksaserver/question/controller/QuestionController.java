package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
import com.picksa.picksaserver.question.dto.response.QuestionDeleteResponse;
import com.picksa.picksaserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
