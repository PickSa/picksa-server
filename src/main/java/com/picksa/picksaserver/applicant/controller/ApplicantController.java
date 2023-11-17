package com.picksa.picksaserver.applicant.controller;

import com.picksa.picksaserver.applicant.OrderCondition;
import com.picksa.picksaserver.applicant.dto.response.ApplicantAllResponse;
import com.picksa.picksaserver.applicant.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    @GetMapping("/all")
    public ResponseEntity<ApplicantAllResponse> getAllApplicants(@RequestParam(required = false) String order) {
        OrderCondition orderCondition = getOrderCondition(order);
        ApplicantAllResponse response = applicantService.getAllApplicants(orderCondition);

        return ResponseEntity.ok(response);
    }

    private OrderCondition getOrderCondition(String order) {
        if (StringUtils.hasText(order)) {
            return OrderCondition.from(order.toUpperCase());
        }
        return null;
    }

}
