package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.QuestionDetermine;
import com.picksa.picksaserver.question.dto.response.QuestionCreateResponse;
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
            @RequestHeader("userId") Long userId,
            @RequestBody QuestionCreateRequest request
    ) {
        QuestionCreateResponse response = questionService.createQuestion(userId, request);
        return ResponseEntity.created(null).body(response);
    }

    @PatchMapping("/final")
    public ResponseEntity<List<QuestionDetermine>> updateFinal (
            @RequestHeader("userId") Long userId,
            @RequestBody List<QuestionDetermine> requests
            ) {
        List<QuestionDetermine> determinedQuestions = questionService.determineQuestions(userId, requests);
        return ResponseEntity.ok(determinedQuestions);
    }

}
