package com.picksa.picksaserver.application.repository;

import com.picksa.picksaserver.application.dto.response.ApplicationDetailResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationQueryRepository {

    List<ApplicationDetailResponse> getApplicationCreatedBetween(LocalDateTime after, LocalDateTime until);

    List<ApplicationDetailResponse> getApplicationsUntil(LocalDateTime until);

}
