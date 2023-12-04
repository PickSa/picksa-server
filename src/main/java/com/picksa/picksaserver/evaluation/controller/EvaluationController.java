package com.picksa.picksaserver.evaluation.controller;

import com.picksa.picksaserver.evaluation.dto.request.DecideRequest;
import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
import com.picksa.picksaserver.evaluation.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {

    private final EvaluationService service;

    @PostMapping("/{applicantId}")
    public ResponseEntity<EvaluationResponse> create(
            @PathVariable(name = "applicantId") Long applicantId,
            @RequestBody EvaluationRequest evaluationRequest) {
        EvaluationResponse response = service.createEvaluation(applicantId, evaluationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{evaluationId}")
    public ResponseEntity<EvaluationResponse> get(@PathVariable(name = "evaluationId") Long evaluationId) {
        return ResponseEntity.ok(service.getEvaluation(evaluationId));
    }

    @PatchMapping("/{evaluationId}")
    public ResponseEntity<EvaluationResponse> update(
            @PathVariable(name = "evaluationId") Long evaluationId,
            @RequestHeader(name = "userId") Long userId,
            @RequestBody EvaluationRequest request
    ) {
        EvaluationResponse response = service.updateEvaluation(evaluationId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<?> getByApplicant(@PathVariable(name = "applicantId") Long applicantId) {
        return ResponseEntity.ok(service.getEvaluationByApplicant(applicantId));
    }

    @PatchMapping("/final/{applicant_id}")
    public ResponseEntity<?> decide(
        @PathVariable(name = "applicantId") Long applicantId,
        @RequestHeader(name = "managerId") Long managerId,
        @RequestBody DecideRequest decideRequest
    ) {
        return ResponseEntity.ok(service.decideEvaluation(applicantId, managerId, decideRequest));
    }

    }

}
