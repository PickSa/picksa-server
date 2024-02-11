package com.picksa.picksaserver.application.controller;

import com.picksa.picksaserver.application.service.ApplicationConvertService;
import com.picksa.picksaserver.application.service.OriginalApplicationConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/applications")
public class ApplicationController {

    private final OriginalApplicationConvertService originalApplicationConvertService;
    private final ApplicationConvertService applicationConvertService;

    @PostMapping("/original")
    public ResponseEntity<Void> convertOriginalToApplication() {
        originalApplicationConvertService.convertOriginalToApplication();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/applicant")
    public ResponseEntity<Void> convertApplicationToApplicant() {
        applicationConvertService.convertApplicationToApplicant();
        return ResponseEntity.ok().build();
    }

}
