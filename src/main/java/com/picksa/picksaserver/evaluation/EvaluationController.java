package com.picksa.picksaserver.evaluation;

import com.picksa.picksaserver.evaluation.dto.request.EvaluationRequest;
import com.picksa.picksaserver.evaluation.dto.response.EvaluationResponse;
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
    public ResponseEntity<EvaluationResponse> create (
        @PathVariable(name = "applicantId") Long applicantId,
        @RequestHeader(name = "managerId") Long managerId,
        @RequestBody EvaluationRequest evaluationRequest) {
        EvaluationResponse response = service.createEvaluation(applicantId, managerId,
            evaluationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<?> getByApplicant(@PathVariable(name = "applicantId") Long applicantId) {
        return ResponseEntity.ok(service.getByApplicant(applicantId));
    }

    @GetMapping("/{evaluationId}")
    public ResponseEntity<EvaluationResponse> get (
        @PathVariable(name = "evaluationId") Long evaluationId
    ) {
        return ResponseEntity.ok(service.getEvaluation(evaluationId));
    }

    @PatchMapping("/{evaluationId}")
    public ResponseEntity<EvaluationResponse> update (
        @PathVariable(name = "evaluationId") Long evaluationId,
        @RequestHeader(name = "managerId") Long managerId,
        @RequestBody EvaluationRequest request
    ) {
        EvaluationResponse response = service.updateEvaluation(evaluationId, managerId, request);
        return ResponseEntity.ok(response);
    }

}
