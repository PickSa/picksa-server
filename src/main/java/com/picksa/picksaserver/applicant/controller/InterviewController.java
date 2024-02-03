package com.picksa.picksaserver.applicant.controller;

import com.picksa.picksaserver.applicant.dto.request.InterviewScheduleCreateRequest;
import com.picksa.picksaserver.applicant.dto.response.InterviewScheduleResponse;
import com.picksa.picksaserver.applicant.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/schedules")
    public ResponseEntity<Void> createInterviewSchedule(@RequestBody List<InterviewScheduleCreateRequest> request) {
        interviewService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<InterviewScheduleResponse>> getInterviewSchedules() {
        List<InterviewScheduleResponse> response = interviewService.getInterviewSchedulesOfThisYear();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/schedules")
    public ResponseEntity<Void> updateInterviewSchedule(@RequestBody List<InterviewScheduleCreateRequest> request) {
        interviewService.update(request);
        return ResponseEntity.ok().build();
    }

}
