package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.question.dto.request.QuestionCreateRequest;
import com.picksa.picksaserver.question.dto.request.QuestionUpdateFinalRequest;
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
            @RequestHeader("managerId") Long managerId,
            @RequestBody QuestionCreateRequest request
    ) {
        QuestionCreateResponse response = questionService.createQuestion(managerId, request);
        return ResponseEntity.created(null).body(response);
    }

    @PatchMapping("/final")
    public ResponseEntity<Void> updateFinal (
            @RequestHeader("managerId") Long managerId,
            @RequestBody List<QuestionUpdateFinalRequest> requests
            ) {
        questionService.updateFianlQuestion(managerId, requests);
        return ResponseEntity.ok().build();
    }

}
