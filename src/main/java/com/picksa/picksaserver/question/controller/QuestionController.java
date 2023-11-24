package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.question.dto.request.QuestionRequest;
import com.picksa.picksaserver.question.dto.response.QuestionResponse;
import com.picksa.picksaserver.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<QuestionResponse> create (
            @RequestHeader("managerId") Long managerId,
            @RequestBody QuestionRequest request
    ) {
        QuestionResponse response = questionService.createQuestion(managerId, request);
        return ResponseEntity.created(null).body(response);
    }
}
