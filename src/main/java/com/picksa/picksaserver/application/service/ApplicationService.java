package com.picksa.picksaserver.application.service;

import com.picksa.picksaserver.application.dto.response.ApplicationDetailResponse;
import com.picksa.picksaserver.application.dto.response.ApplicationSyncResponse;
import com.picksa.picksaserver.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationSyncResponse getApplicationsAfter(LocalDateTime after) {
        LocalDateTime until = LocalDateTime.now();
        List<ApplicationDetailResponse> applicationDetailResponses = applicationRepository.getApplicationCreatedBetween(after, until);

        return ApplicationSyncResponse.of(until, applicationDetailResponses);
    }

    public ApplicationSyncResponse getAllApplications() {
        LocalDateTime until = LocalDateTime.now();
        List<ApplicationDetailResponse> applicationDetailResponses = applicationRepository.getApplicationsUntil(until);
        return ApplicationSyncResponse.of(until, applicationDetailResponses);
    }

}
